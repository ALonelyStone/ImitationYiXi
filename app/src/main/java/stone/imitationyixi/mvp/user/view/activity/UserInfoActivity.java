package stone.imitationyixi.mvp.user.view.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mylhyl.superdialog.SuperDialog;
import com.yalantis.contextmenu.lib.MenuObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import stone.imitationyixi.ImitationYiXiApplication;
import stone.imitationyixi.R;
import stone.imitationyixi.bean.LectureBean;
import stone.imitationyixi.bean.UserBean;
import stone.imitationyixi.common.SharedPreferenceConstant;
import stone.imitationyixi.database.UserDataDao;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;
import stone.imitationyixi.mvp.lecture.view.activity.LectureActivity;
import stone.imitationyixi.mvp.login.view.activity.LoginActivity;
import stone.imitationyixi.mvp.user.adapter.BaseUserDataAdapter;
import stone.imitationyixi.mvp.user.adapter.CollectionAdapter;
import stone.imitationyixi.mvp.user.adapter.DownloadAdapter;
import stone.imitationyixi.mvp.user.adapter.ReplyAdapter;
import stone.imitationyixi.mvp.user.adapter.listener.UserItemClickListener;
import stone.imitationyixi.mvp.user.presenter.UserInfoPersenter;
import stone.imitationyixi.utils.ImageUtils;
import stone.imitationyixi.utils.IntentUtils;
import stone.imitationyixi.utils.SharedPreferenceUtils;
import stone.imitationyixi.utils.TypefaceUtils;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener, IUserInfoView, UserItemClickListener {

    private ImageView userIcon;
    private TextView userName;
    private TextView userDes;
    private ImageView ivDown;
    private ImageView ivColl;
    private ImageView ivReplay;
    private UserInfoPersenter mPersenter;
    private UserBean mInfoBean;
    private ImageView ivBack;
    private RelativeLayout userPanel;
    private RecyclerView itemRecycler;
    private CollectionAdapter mCollectionAdapter;
    private DownloadAdapter mDownloadAdapter;
    private ReplyAdapter mReplyAdapter;
    private ArrayList<LectureBean> mCollList;
    private ArrayList<LectureBean> mDownList;
    private ArrayList<LectureBean> mReplylist;
    private ImageView ivDownLine;
    private ImageView ivCollLine;
    private ImageView ivReplayLine;

    @Override
    protected int getContentView() {
        saveData();
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {
        super.initView();
        ivDown = (ImageView) findViewById(R.id.iv_down);
        ivColl = (ImageView) findViewById(R.id.iv_coll);
        ivReplay = (ImageView) findViewById(R.id.iv_replay);
        ivDown.setImageResource(R.drawable.xiazai_red);
        ivColl.setImageResource(R.drawable.shoucang);
        ivReplay.setImageResource(R.drawable.pinglun);

        userIcon = (ImageView) findViewById(R.id.user_icon);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        userName = (TextView) findViewById(R.id.user_name);
        userDes = (TextView) findViewById(R.id.user_des);

        userPanel = (RelativeLayout) findViewById(R.id.user_panel);

        itemRecycler = (RecyclerView) findViewById(R.id.item_recycler);
        itemRecycler.setLayoutManager(new LinearLayoutManager(this));
        mCollectionAdapter = new CollectionAdapter();
        mDownloadAdapter = new DownloadAdapter();
        mReplyAdapter = new ReplyAdapter();
        mCollectionAdapter.setOnItemClick(this);
        mDownloadAdapter.setOnItemClick(this);
        mReplyAdapter.setOnItemClick(this);

        ivDownLine = (ImageView) findViewById(R.id.iv_down_line);
        ivCollLine = (ImageView) findViewById(R.id.iv_coll_line);
        ivReplayLine = (ImageView) findViewById(R.id.iv_replay_line);
    }

    @Override
    protected Subscription initNetwork() {
        mPersenter = new UserInfoPersenter(this);
        mPersenter.getIDList(mInfoBean.getId());
        return null;
    }

    @Override
    protected void initData() {
        super.initData();
        mInfoBean = ImitationYiXiApplication.getUserInfo();
        userIcon.measure(0, 0);
        int dimater = userIcon.getMeasuredHeight() > userIcon.getMeasuredWidth() ? userIcon.getMeasuredHeight() : userIcon.getMeasuredWidth();
        ImageUtils.loadCircleImageAndCache(this, mInfoBean.getPic(), userIcon, dimater);
        ImageUtils.loadImageAndCache(this, mInfoBean.getBackground(), ivBack, 0, 0);

        userName.setText(mInfoBean.getNickname());
        userDes.setText(mInfoBean.getDesc());
    }

    @Override
    protected void initListener() {
        ivDown.setOnClickListener(this);
        ivColl.setOnClickListener(this);
        ivReplay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ivDown.setImageResource(R.drawable.xiazai);
        ivColl.setImageResource(R.drawable.shoucang);
        ivReplay.setImageResource(R.drawable.pinglun);
        ivDownLine.setVisibility(View.GONE);
        ivCollLine.setVisibility(View.GONE);
        ivReplayLine.setVisibility(View.GONE);
        switch (v.getId()) {
            case R.id.iv_down:
                loadDownload();
                break;
            case R.id.iv_coll:
                loadCollection();
                break;
            case R.id.iv_replay:
                loadReplay();
                break;
        }
    }

    /**
     * 加载回复数据
     */
    private void loadReplay() {
        ivReplay.setImageResource(R.drawable.pinglun_red);
        ivReplayLine.setVisibility(View.VISIBLE);
        itemRecycler.setAdapter(null);
        itemRecycler.setAdapter(mReplyAdapter);
        if (mReplylist.size() <= 0) {
            showToast("没有任何评论,跟大家交流下吧!");
            itemRecycler.setNestedScrollingEnabled(false);
            return;
        }
        itemRecycler.setNestedScrollingEnabled(true);
        mReplyAdapter.upData(mReplylist);
        mReplyAdapter.notifyDataSetChanged();
    }

    /**
     * 加载收藏数据
     */
    private void loadCollection() {
        ivColl.setImageResource(R.drawable.shoucang_red);
        ivCollLine.setVisibility(View.VISIBLE);
        itemRecycler.setAdapter(null);
        itemRecycler.setAdapter(mCollectionAdapter);
        if (mCollList.size() <= 0) {
            showToast("还没有收藏,快去收藏一个吧!");
            itemRecycler.setNestedScrollingEnabled(false);
            return;
        }
        itemRecycler.setNestedScrollingEnabled(true);
        mCollectionAdapter.upData(mCollList);
        mCollectionAdapter.notifyDataSetChanged();
    }

    /**
     * 加载下载数据
     */
    private void loadDownload() {
        ivDown.setImageResource(R.drawable.xiazai_red);
        ivDownLine.setVisibility(View.VISIBLE);
        itemRecycler.setAdapter(null);
        itemRecycler.setAdapter(mDownloadAdapter);
        if (mDownList.size() <= 0) {
            showToast("还没有下载过演讲,快去浏览下吧!");
            itemRecycler.setNestedScrollingEnabled(false);
            return;
        }
        itemRecycler.setNestedScrollingEnabled(true);
        mDownloadAdapter.upData(mDownList);
        mDownloadAdapter.notifyDataSetChanged();
    }


    /**
     * 收藏列表的数据返回
     *
     * @param beanlist
     */
    @Override
    public void returnCollList(ArrayList<LectureBean> beanlist) {
        mCollList = beanlist;
        if (mCollectionAdapter != null) {
            mCollectionAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 下载列表数据返回
     *
     * @param beanlist
     */
    @Override
    public void returnDownList(ArrayList<LectureBean> beanlist) {
        mDownList = beanlist;
        itemRecycler.setNestedScrollingEnabled(beanlist.size() <= 0 ? false : true);
        if (mDownloadAdapter != null) {
            itemRecycler.setAdapter(mDownloadAdapter);
            mDownloadAdapter.upData(mDownList);
            mDownloadAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 回复列表数据返回
     *
     * @param beanlist
     */
    @Override
    public void returnReplyList(ArrayList<LectureBean> beanlist) {
        mReplylist = beanlist;
        if (mReplyAdapter != null) {
            mReplyAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 打印toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        Snackbar.make(itemRecycler, msg, Snackbar.LENGTH_SHORT).show();
        if (mReplyAdapter != null) {
            mReplyAdapter.notifyDataSetChanged();
        }

    }

    public void saveData() {
        UserDataDao dao = new UserDataDao(this);
        dao.saveCollSpeech(403);
        dao.saveCollSpeech(403);

        dao.saveReplySpeech(403);
        Intent intent = new Intent(this, LectureActivity.class);
        intent.putExtra("lectureId", 415);
        //        startActivity(intent);

    }

    /**
     * item的点击事件
     *
     * @param position
     */
    @Override
    public void onClick(View v, int position) {
        BaseUserDataAdapter adapter = (BaseUserDataAdapter) itemRecycler.getAdapter();
        Intent intent = new Intent(this, LectureActivity.class);
        intent.putExtra("lectureId", adapter.mData.get(position).getId());
        startActivity(intent);
    }

    @Override
    protected List<MenuObject> getMenuObjects() {
        List<MenuObject> menuObjects = super.getMenuObjects();
        MenuObject menuObject = new MenuObject();
        menuObject.setResource(R.mipmap.exit);
        menuObject.setTitle(TypefaceUtils.setTypefaceInCharSequence("退出", mPath));
        menuObjects.add(menuObject);
        return menuObjects;
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        if (position == 1) {
            showExitDialog();//退出按钮
        }
    }

    /**
     * 退出帐号
     */
    private void showExitDialog() {
        DialogFragment build = new SuperDialog.Builder(this)
                .setRadius(10)
                .setTitle("确定退出?")
                .setMessage("退出后将返回登录页面")
                .setNegativeButton("再看看吧!", null)
                .setPositiveButton("确定", new SuperDialog.OnClickPositiveListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferenceUtils.putString(UserInfoActivity.this, SharedPreferenceConstant.UID, null);
                        ImitationYiXiApplication.setsUserInfo(new UserBean());
                        ImitationYiXiApplication.getUserInfo().setLogin(false);
                        IntentUtils.startActivity(UserInfoActivity.this, LoginActivity.class);
                        finish();
                    }
                }).build();
    }

    @Override
    protected boolean isHasBack() {
        return true;
    }

    @Override
    public boolean isHasMore() {
        return true;
    }
}
