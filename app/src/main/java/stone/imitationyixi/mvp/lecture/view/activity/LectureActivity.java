package stone.imitationyixi.mvp.lecture.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.R;
import stone.imitationyixi.bean.CommentsBean;
import stone.imitationyixi.bean.LectureBean;
import stone.imitationyixi.bean.RelatedBean;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;
import stone.imitationyixi.mvp.comments.view.activity.CommentsActivity;
import stone.imitationyixi.mvp.comments.view.adapter.CommentAdapter;
import stone.imitationyixi.mvp.lecture.presenter.LecturePresenter;
import stone.imitationyixi.mvp.lecture.view.ILectureView;
import stone.imitationyixi.mvp.lecture.view.RelatedAdapter;
import stone.imitationyixi.mvp.speech.view.activity.SpeechActivity;
import stone.imitationyixi.utils.ImageUtils;
import stone.imitationyixi.utils.ToastUtils;
import stone.imitationyixi.widget.NoScrollListView;

import static stone.imitationyixi.R.id.lecturer_portrait;

/**
 * @author Kotori
 * @time 2017/3/6  15:25
 * @desc ${TODD}
 */

public class LectureActivity extends BaseActivity implements ILectureView, View.OnClickListener, AdapterView.OnItemClickListener {

    private LecturePresenter mLecturePresenter;
    private ImageView mLectureBackground;
    private ImageView mLecturerPortrait;
    private TextView mLecturer;
    private TextView mLecturerDesc;
    private RelativeLayout mLectureHeader;
    private TextView mLectureTitle;
    private TextView mLectureTime;
    private TextView mLectureDesc;
    private ImageView mLectureCover;
    private VideoView mVideoView;
    private FrameLayout mVideoFrame;
    private ImageView mPlayVideo;
    private TextView mLecturePlayedNumber;
    private TextView mLectureLikedNumber;
    private ImageView mDownloadImage;
    private LinearLayout mDownloadLayout;
    private ImageView mBookmarkImage;
    private LinearLayout mBookmarkLayout;
    private ImageView mLikeImage;
    private LinearLayout mLikeLayout;
    private TextView mLectureExcerpt;
    private TextView mShowAllContent;
    private EditText mAddComment;
    private ImageView mSendCommentImage;
    private NoScrollListView mCommentList;
    private TextView mCommentNumber;
    private TextView mShowAllComments;
    private CommentAdapter mCommentAdapter;
    private LinearLayout mShareLayout;
    private CommentsBean mCommentsBean;
    private LectureBean mLectureBean;
    private int mLectureId;
    private List<RelatedBean> mRelatedBeen;
    private NoScrollListView mBottomView;

    @Override
    protected int getContentView() {
        return R.layout.activity_lecture;
    }

    @Override
    protected void initView() {
        super.initView();
        mLectureBackground = (ImageView) findViewById(R.id.lecture_background);
        mLecturerPortrait = (ImageView) findViewById(lecturer_portrait);
        mLecturer = (TextView) findViewById(R.id.lecturer);
        mLecturerDesc = (TextView) findViewById(R.id.lecturer_desc);
        mLectureTitle = (TextView) findViewById(R.id.lecture_title);
        mLectureTime = (TextView) findViewById(R.id.lecture_time);
        mLectureDesc = (TextView) findViewById(R.id.lecture_desc);
        mLectureCover = (ImageView) findViewById(R.id.lecture_cover);
        mPlayVideo = (ImageView) findViewById(R.id.play_video);
        mLecturePlayedNumber = (TextView) findViewById(R.id.lecture_played_number);
        mLectureLikedNumber = (TextView) findViewById(R.id.lecture_liked_number);
        mDownloadImage = (ImageView) findViewById(R.id.download_image);
        mBookmarkImage = (ImageView) findViewById(R.id.bookmark_image);
        mShareLayout = (LinearLayout) findViewById(R.id.share_layout);
        mLikeImage = (ImageView) findViewById(R.id.like_image);
        mLectureExcerpt = (TextView) findViewById(R.id.lecture_excerpt);
        mShowAllContent = (TextView) findViewById(R.id.show_all_content);
        mAddComment = (EditText) findViewById(R.id.add_comment);
        mSendCommentImage = (ImageView) findViewById(R.id.send_comment_image);
        mCommentList = (NoScrollListView) findViewById(R.id.comment_list);
        mCommentNumber = (TextView) findViewById(R.id.comment_number);
        mShowAllComments = (TextView) findViewById(R.id.show_all_comments);
        mBottomView = (NoScrollListView) findViewById(R.id.bottom_listview);
    }

    @Override
    protected Subscription initNetwork() {
        mLecturePresenter.netWorkForBottom(mLectureId);
        mLecturePresenter.netWorkForComments(String.valueOf(mLectureId));
        return mLecturePresenter.getNetworkSubscription(String.valueOf(mLectureId));
    }
    /**
     * 拿到评论的bean
     */
    @Override
    public void doNetworkSmallCommentsFinish(List<CommentsBean> list) {
        CommentAdapter commentAdapter = new CommentAdapter(mCommentList, list, mPath);
        commentAdapter.isSmallComments(true);
        mCommentList.setAdapter(commentAdapter);
    }

    @Override
    public void doNetworkBottomFinish(List<RelatedBean> relatedBeanList) {
        mRelatedBeen = relatedBeanList;
        RelatedAdapter relatedAdapter = new RelatedAdapter(mBottomView, relatedBeanList, mPath);
        mBottomView.setAdapter(relatedAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        mLectureId = intent.getIntExtra("lectureId", 0);
        mLecturePresenter = new LecturePresenter(this);
        mLecturer.setFocusable(true);
        mLecturer.setFocusableInTouchMode(true);
        mLecturer.requestFocus();
    }

    @Override
    protected boolean isHasBack() {
        return true;
    }

    @Override
    public boolean isHasMore() {
        return false;
    }

    @Override
    public void showNetworkError() {
        ToastUtils.showShortToast("网络连接失败,请稍后重试!", mPath);
    }

    @Override
    public void doNetworkFinish(LectureBean bean) {
        mLectureBean = bean;
        ImageUtils.loadImageAndCache(this, bean.getBackground(), mLectureBackground, mLectureBackground.getWidth(), mLectureBackground.getHeight());
        ImageUtils.loadCircleImageAndCache(this, bean.getLecturer().getPic(), mLecturerPortrait, mLecturerPortrait.getWidth());
        mLecturer.setText(bean.getLecturer().getNickname());
        mLecturerDesc.setText(bean.getLecturer().getDesc());
        mLectureTitle.setText(bean.getTitle());
        mLectureTime.setText(bean.getTime());
        mLectureDesc.setText(bean.getDesc());
        ImageUtils.loadImageAndCache(this, bean.getCover(), mLectureCover, mLectureCover.getWidth(), mLectureCover.getHeight());
        mLecturePlayedNumber.setText("播放了" + bean.getViewnum() + "次");
        mLectureLikedNumber.setText(bean.getLikenum() + "人点赞");
        String purecontent = bean.getPurecontent();
        if (purecontent.length() >= 100) {
            mLectureExcerpt.setText(purecontent.substring(0, 100));
        }

        mCommentNumber.setText("共" + bean.getCmtnum() + "条");

    }

    @Override
    protected void initListener() {
        mDownloadImage.setOnClickListener(this);
        mBookmarkImage.setOnClickListener(this);
        mPlayVideo.setOnClickListener(this);
        mShareLayout.setOnClickListener(this);
        mLikeImage.setOnClickListener(this);
        mShowAllContent.setOnClickListener(this);
        mSendCommentImage.setOnClickListener(this);
        mShowAllComments.setOnClickListener(this);
        mBottomView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_video:
                //TODO 播放视频
                break;
            case R.id.download_image:
                //TODO 下载
                break;
            case R.id.bookmark_image:
                //TODO 收藏
                break;
            case R.id.share_layout:
                //分享
                break;
            case R.id.like_image:
                //TODO 喜欢
                break;
            case R.id.show_all_content:
                //展示全文
                Intent intentToSpeech = new Intent(this, SpeechActivity.class);
                intentToSpeech.putExtra("title", mLectureBean.getTitle());
                intentToSpeech.putExtra("name", mLectureBean.getLecturer().getNickname());
                intentToSpeech.putExtra("content", mLectureBean.getPurecontent());
                startActivity(intentToSpeech);
                break;
            case R.id.send_comment_image:
                //TODO 发表评论
                break;
            case R.id.show_all_comments:
                //打开全部评论
                Intent intent = new Intent(this, CommentsActivity.class);
                intent.putExtra("lectureId", 415);
                mCommentList = null;
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int lectureId = mRelatedBeen.get(position).getId();
        Intent intent = new Intent(LectureActivity.this, LectureActivity.class);
        intent.putExtra("lectureId",lectureId);
        startActivity(intent);
    }
}
