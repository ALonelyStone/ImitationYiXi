package stone.imitationyixi.mvp.base.view.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rx.Subscription;
import stone.imitationyixi.R;
import stone.imitationyixi.mvp.base.presenter.BasePresenter;
import stone.imitationyixi.mvp.base.presenter.IBasePresenter;
import stone.imitationyixi.mvp.base.view.IBaseView;
import stone.imitationyixi.mvp.main.view.activity.MainActivity;
import stone.imitationyixi.utils.ToastUtils;
import stone.imitationyixi.utils.TypefaceUtils;
import stone.imitationyixi.utils.ViewUtils;


/**
 * @author stone
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView, OnMenuItemLongClickListener, OnMenuItemClickListener {

    protected Subscription mSubscription;
    private FragmentManager mFragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    private boolean mIsClose = false;
    protected static String mPath;
    private IBasePresenter mBasePresenter;
    private MenuParams mMenuParams;

    /**
     * 返回应用设置的字体
     */
    public static String getPath() {
        return mPath;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initToolbar();
        initMenuFragment();
        mSubscription = initNetwork();
        initAnimation();
        initListener();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mBasePresenter.setTypeface();
        List<MenuObject> menuObjects = getMenuObjects();
        System.out.println("onRestart -- getMenuObjects = " + menuObjects);
        mMenuParams.setMenuObjects(menuObjects);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消网络请求
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (mFragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(mFragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            if (!mIsClose && this.getClass() == MainActivity.class) {
                mIsClose = true;
                ToastUtils.showShortToast("再次点击退出！", mPath);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mIsClose = false;
                    }
                }, 2000);
            } else {
                super.onBackPressed();
            }
        }
    }

    /**
     * 获取页面根布局
     */
    protected abstract int getContentView();

    /**
     * 初始化视图
     */
    protected void initView() {
        setContentView(getContentView());

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    /**
     * 初始化工具栏（子类不得重写）
     */
    protected final void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.app_toolbar);
        if (isHasMore()) {
            setSupportActionBar(mToolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        if (isHasBack()) {
            mToolbar.setNavigationIcon(R.mipmap.icn_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } else {
            mToolbar.setNavigationIcon(getToolbarNavigationIcon());
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onToolbarNavigationClick();
                }
            });
        }
        if (isHasBackgroundColor()) {
            mToolbar.setBackgroundColor(ViewUtils.getColor(R.color.app_red));
        } else {
            mToolbar.setBackgroundColor(Color.TRANSPARENT);
        }
        if (isHasTitle()) {
            TextView title = (TextView) mToolbar.findViewById(R.id.app_title);
            title.setVisibility(View.VISIBLE);
            title.setText(getToolbarTitle());
        }
        if (isHasSearch()) {
            LinearLayout search = (LinearLayout) mToolbar.findViewById(R.id.search_ll);
            search.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置工具栏标题
     */
    public CharSequence getToolbarTitle() {
        return "";
    }

    /**
     * 设置工具栏左边按钮图案（不是返回按钮的时候）
     */
    protected Drawable getToolbarNavigationIcon() {
        return ViewUtils.getResources().getDrawable(R.mipmap.icn_head);
    }

    /**
     * 设置工具栏左边按钮的点击事件（不是返回按钮的时候）
     */
    protected void onToolbarNavigationClick() {
        //默认不实现
    }

    /**
     * 是否设置工具栏背景颜色
     */
    public boolean isHasBackgroundColor() {
        return false;
    }

    public boolean isHasTitle() {
        return true;
    }

    public boolean isHasSearch() {
        return false;
    }

    /**
     * 是否设置工具栏左边按钮为返回按钮
     */
    protected boolean isHasBack() {
        return false;
    }

    /**
     * 是否设置工具栏右边更多按钮是否显示
     */
    public boolean isHasMore() {
        return true;
    }

    /**
     * 初始化菜单碎片（子类不得重写）
     */
    protected final void initMenuFragment() {
        mMenuParams = new MenuParams();
        mMenuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));

        List<MenuObject> menuObjects = getMenuObjects();
        mMenuParams.setMenuObjects(menuObjects);
        mMenuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(mMenuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    /**
     * 设置菜单项（已添加取消按钮）<p>
     * ps.设置多于一定数量时，菜单栏将可以滚动
     */
    protected List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = new ArrayList<>();
        MenuObject close = new MenuObject();
        close.setResource(R.mipmap.icn_close);

        menuObjects.add(close);
        return menuObjects;
    }

    /**
     * 初始化数据
     */
    protected void initData() {
        mFragmentManager = getSupportFragmentManager();
        mBasePresenter = new BasePresenter(this);
        mBasePresenter.setTypeface();
    }

    public void setTypeface(String path) {
        mPath = path;
        TypefaceUtils.setTypefaceInActivity(this, path);
    }

    /**
     * 初始化网络请求
     */
    protected Subscription initNetwork() {
        //默认不实现
        return null;
    }

    /**
     * 初始化动画
     */
    protected void initAnimation() {
        //默认不实现
    }

    /**
     * 初始化监听
     */
    protected void initListener() {
        //默认不实现
    }

    /**
     * 菜单项单击事件（0为取消按钮，不需要设置事件）
     */
    @Override
    public void onMenuItemClick(View clickedView, int position) {
        //默认不实现
    }

    /**
     * 菜单项长按事件（0为取消按钮，不需要设置事件）
     */
    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        //默认不实现
    }

}
