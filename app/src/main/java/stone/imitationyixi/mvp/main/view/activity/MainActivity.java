package stone.imitationyixi.mvp.main.view.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.yalantis.contextmenu.lib.MenuObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import stone.imitationyixi.ImitationYiXiApplication;
import stone.imitationyixi.R;
import stone.imitationyixi.bean.UserBean;
import stone.imitationyixi.common.SharedPreferenceConstant;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;
import stone.imitationyixi.mvp.branches.view.fragment.BranchesFragment;
import stone.imitationyixi.mvp.event.view.fragment.EventFragment;
import stone.imitationyixi.mvp.lecturer.view.fragment.LecturerFragment;
import stone.imitationyixi.mvp.lectures.view.fragment.LecturesFragment;
import stone.imitationyixi.mvp.login.view.DoLogin;
import stone.imitationyixi.mvp.login.view.ILogin;
import stone.imitationyixi.mvp.login.view.activity.LoginActivity;
import stone.imitationyixi.mvp.main.presenter.MainPresenter;
import stone.imitationyixi.mvp.main.view.IMainView;
import stone.imitationyixi.mvp.record.view.fragment.RecordFragment;
import stone.imitationyixi.mvp.search.view.activity.SearchActivity;
import stone.imitationyixi.mvp.setting.view.activity.SettingActivity;
import stone.imitationyixi.mvp.user.view.activity.UserInfoActivity;
import stone.imitationyixi.utils.IntentUtils;
import stone.imitationyixi.utils.SharedPreferenceUtils;
import stone.imitationyixi.utils.ToastUtils;
import stone.imitationyixi.utils.TypefaceUtils;
import stone.imitationyixi.utils.ViewUtils;

public class MainActivity extends BaseActivity implements IMainView {

    private BottomBar mBottomBar;
    private List<Fragment> mFragments;
    private Fragment mCurrentFragment;
    private MainPresenter mMainPresenter;

    @Override
    public void showNetworkError() {
        ToastUtils.showShortToast("网络连接失败，请检查一下网络", BaseActivity.getPath());
    }

    @Override
    public void setNavigationIcon(GlideDrawable drawable) {
        ((Toolbar) findViewById(R.id.app_toolbar))
                .setNavigationIcon(drawable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserBean userBean = ImitationYiXiApplication.getUserInfo();
        if (userBean != null && userBean.isLogin()) {
            mSubscription = mMainPresenter.getNetworkSubscription();
        } else {
            ((Toolbar) findViewById(R.id.app_toolbar))
                    .setNavigationIcon(R.mipmap.icn_head);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();

        mBottomBar = (BottomBar) findViewById(R.id.main_bb);

        //创建Fragment对象集合
        mFragments = new ArrayList<>();
        mFragments.add(new LecturesFragment()); //讲座
        mFragments.add(new RecordFragment());//记录
        mFragments.add(new LecturerFragment());//讲师
        mFragments.add(new EventFragment());//活动
        mFragments.add(new BranchesFragment());//枝桠
    }

    @Override
    protected void initData() {
        super.initData();
        mMainPresenter = new MainPresenter(this);
        new DoLogin(new ILogin() {
            @Override
            public String toLogin() {
                return SharedPreferenceUtils.getString(ViewUtils.getContext(), SharedPreferenceConstant.UID);
            }
        });
    }

    @Override
    protected Subscription initNetwork() {
        return super.initNetwork();
    }

    @Override
    protected void initListener() {
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_lecture://讲座
                        showFragment(mFragments.get(0), "Lecture");
                        break;
                    case R.id.tab_record://记录
                        showFragment(mFragments.get(1), "Record");
                        break;
                    case R.id.tab_lecturer://讲者
                        showFragment(mFragments.get(2), "Lecturers");
                        break;
                    case R.id.tab_event://活动
                        showFragment(mFragments.get(3), "Activity");
                        break;
                    case R.id.tab_branch://枝桠
                        showFragment(mFragments.get(4), "Branches");
                        break;
                }

            }
        });
    }

    private void showFragment(Fragment f, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (f.isAdded()) {//判断是否添加过
            //已经添加过,隐藏或显示
            transaction.hide(mCurrentFragment).show(f).commitAllowingStateLoss();
            mCurrentFragment = f;
        } else {
            //未添加过,添加新的Fragment,并隐藏当前显示的
            if (mCurrentFragment == null) {
                transaction.add(R.id.main_fragment, f, tag).commitAllowingStateLoss();
            } else {
                transaction.add(R.id.main_fragment, f, tag).hide(mCurrentFragment).commitAllowingStateLoss();
            }
            mCurrentFragment = f;
        }
    }

    @Override
    protected List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = super.getMenuObjects();

        MenuObject menuObject = new MenuObject();
        menuObject.setResource(R.mipmap.icn_search);
        menuObject.setTitle(TypefaceUtils.setTypefaceInCharSequence("搜索", mPath));
        menuObjects.add(menuObject);

        menuObject = new MenuObject();
        menuObject.setResource(R.mipmap.icn_setting);
        menuObject.setTitle(TypefaceUtils.setTypefaceInCharSequence("设置", mPath));
        menuObjects.add(menuObject);

        return menuObjects;
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        switch (position) {
            case 1:
                IntentUtils.startActivity(MainActivity.this, SearchActivity.class);
                break;
            case 2:
                IntentUtils.startActivity(MainActivity.this, SettingActivity.class);
                break;
        }
    }

    @Override
    protected void onToolbarNavigationClick() {
        //判断是否已经登录
        if (ImitationYiXiApplication.getUserInfo().isLogin()) {
            //已经登录
            IntentUtils.startActivity(MainActivity.this, UserInfoActivity.class);
        } else {
            //未登录,跳转到登录页
            IntentUtils.startActivity(MainActivity.this, LoginActivity.class);
        }
    }

}