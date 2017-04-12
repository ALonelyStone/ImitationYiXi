package stone.imitationyixi.mvp.comments.view.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rx.Subscription;
import stone.imitationyixi.ImitationYiXiApplication;
import stone.imitationyixi.R;
import stone.imitationyixi.bean.CommentsBean;
import stone.imitationyixi.bean.UserBean;
import stone.imitationyixi.common.DatabaseConstant;
import stone.imitationyixi.database.CommentsUsersDao;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;
import stone.imitationyixi.mvp.comments.presenter.CommentsPresenter;
import stone.imitationyixi.mvp.comments.view.ICommentsView;
import stone.imitationyixi.mvp.comments.view.adapter.CommentAdapter;
import stone.imitationyixi.mvp.comments.view.listener.EndlessScrollListener;
import stone.imitationyixi.utils.ImageUtils;
import stone.imitationyixi.utils.ToastUtils;

/**
 * @author Kotori
 * @time 2017/3/7  17:05
 * @desc ${TODD}
 */

public class CommentsActivity extends BaseActivity implements ICommentsView {

    private int mLectureId;
    private List<CommentsBean> mCommentsBean;
    private ListView mCommentsListView;
    private CommentsPresenter mCommentsPresenter;
    private CommentAdapter mCommentAdapter;
    private EditText mEditText;
    private ImageView mSendComment;
    private String mRepryNickname;
    private int mUserId;
    private UserBean mUserBean;
    private String mPic;
    private String mNickname;
    private String mTime;
    private String mContentEd;
    private List<Map<String, String>> mLoadUserMsgList;


    @Override
    protected boolean isHasBack() {
        return true;
    }

    @Override
    public boolean isHasMore() {
        return false;
    }

    private void initListViewAndAdapter() {
        mCommentsListView = (ListView) findViewById(R.id.comments_listview);
        mCommentAdapter = new CommentAdapter(mCommentsListView, mCommentsBean, mPath);
        mCommentAdapter.isSmallComments(false);
        mCommentsListView.setAdapter(mCommentAdapter);
        mCommentAdapter.notifyDataSetChanged();
        initListener();
        mCommentsListView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemCount) {
                mCommentsPresenter.getNetworkForLoadMore(mLectureId, page);
            }
        });
    }

    @Override
    protected void initView() {
        super.initView();
        Log.e("stone","initView");
        loadUserDataBase();
        mEditText = (EditText) findViewById(R.id.add_comment);
        mSendComment = (ImageView) findViewById(R.id.send_comment_image);
    }

    private void loadUserDataBase() {
        Log.e("stone","loadUserDataBase");
        CommentsUsersDao commentsUsersDao = new CommentsUsersDao(CommentsActivity.this);
        mLoadUserMsgList = commentsUsersDao.loadUserMsg();
        for (Map<String, String> map : mLoadUserMsgList) {
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                Log.e("stone", entry.getKey()+"    "+entry.getValue());
            }
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_lecture_comments;
    }

    @Override
    protected void initData() {
        super.initData();
        if (ImitationYiXiApplication.getUserInfo().isLogin()){
            mUserId = ImitationYiXiApplication.getUserInfo().getId();
        }
        Intent intent = getIntent();
        mLectureId = intent.getIntExtra("lectureId", 0);

    }

    @Override
    protected void initListener() {

        loadHeadView();

        if (mCommentsListView != null) {
            mCommentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mRepryNickname = mCommentsBean.get(position).getUser().getNickname();
                    mEditText.setHint("回复 " + mRepryNickname + ":");
                    mEditText.setFocusableInTouchMode(true);
                    mEditText.requestFocus();
                }
            });
        }

        mSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ImitationYiXiApplication.getUserInfo().isLogin()) {
                    ToastUtils.showShortToast("请先登录", mPath);
                    return;
                }
                if (TextUtils.isEmpty(mEditText.getText().toString())) {
                    ToastUtils.showShortToast("请输入内容", mPath);
                    return;
                }
                if (TextUtils.isEmpty(mRepryNickname)) {
                    //不是回复性质的评论
                    setHeadView(false);
                } else {
                    //是回复
                    setHeadView(true);
                }
            }
        });


    }

    private void loadHeadView() {
        if(mLoadUserMsgList !=null) {
            for (Map<String, String> map : mLoadUserMsgList) {
                mPic = map.get(DatabaseConstant.CommentsUsersList._USERPIC);
                mNickname = map.get(DatabaseConstant.CommentsUsersList._USERNICKNAME);
                mTime = map.get(DatabaseConstant.CommentsUsersList._TIME);
                mContentEd = map.get(DatabaseConstant.CommentsUsersList._CONTENT);
                mRepryNickname = map.get(DatabaseConstant.CommentsUsersList._TONAME);
                setHeadView(mRepryNickname.equals(0)?false:true);
            }
        }
    }

    public void setHeadView(boolean isReply){
        mUserBean = ImitationYiXiApplication.getUserInfo();
        View view = View.inflate(CommentsActivity.this, R.layout.comment_item, null);
        ImageView userPic = (ImageView) view.findViewById(R.id.comment_user_portrait);
        mPic = mUserBean.getPic();
        if(TextUtils.isEmpty(mPic)) {
            userPic.setImageResource(R.mipmap.base_head90);
        } else {
            ImageUtils.loadCircleImageAndCache(CommentsActivity.this, mPic,userPic,userPic.getWidth());
        }
        TextView userNickName = (TextView)view.findViewById(R.id.comment_user_nickname);
        mNickname = mUserBean.getNickname();
        userNickName.setText(mNickname);
        TextView CommentTime = (TextView) view.findViewById(R.id.comment_time);
        mTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        CommentTime.setText(mTime);
        TextView content = (TextView) view.findViewById(R.id.comment_content);
        mContentEd = mEditText.getText().toString();
        content.setText(mContentEd);
        if(isReply) {
            TextView replyComment = (TextView) view.findViewById(R.id.comment_reply);
            TextView replyCommentNickName = (TextView) view.findViewById(R.id.comment_reply_nickname);
            replyComment.setVisibility(View.VISIBLE);
            replyCommentNickName.setVisibility(View.VISIBLE);
            replyCommentNickName.setText(mRepryNickname);
            initDataBase(mUserBean, mTime, mContentEd,mRepryNickname);
            mCommentsListView.addHeaderView(view);
            return;
        }
        initDataBase(mUserBean, mTime, mContentEd,"0");
        mCommentsListView.addHeaderView(view);
    }

    private void initDataBase(UserBean bean,String time,String content,String toname) {
        CommentsUsersDao commentsUsersDao = new CommentsUsersDao(CommentsActivity.this);
        commentsUsersDao.saveSendUserMsg(bean,time,content,toname);
    }

    @Override
    protected Subscription initNetwork() {
        mCommentsPresenter = new CommentsPresenter(this);
        return mCommentsPresenter.getNetworkSubscription(mLectureId, 1);
    }

    @Override
    public void showNetworkError() {
        ToastUtils.showShortToast("网络连接失败,请稍后重试!", mPath);
    }

    @Override
    public void doNetworkFinish(List<CommentsBean> commentsBeen) {
        mCommentsBean = commentsBeen;
        initListViewAndAdapter();
    }

    @Override
    public void doNetworkForLoadMoreFinish(List<CommentsBean> commentsBeen) {
        mCommentsBean.addAll(commentsBeen);
        mCommentAdapter.notifyDataSetChanged();
    }
}
