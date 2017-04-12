package stone.imitationyixi.mvp.splash.view.activity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import rx.Subscription;
import stone.imitationyixi.R;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;
import stone.imitationyixi.mvp.main.view.activity.MainActivity;
import stone.imitationyixi.mvp.splash.presenter.ISplashPresenter;
import stone.imitationyixi.mvp.splash.presenter.SplashPresenter;
import stone.imitationyixi.mvp.splash.view.ISplashView;
import stone.imitationyixi.utils.IntentUtils;
import stone.imitationyixi.utils.ToastUtils;
import stone.imitationyixi.utils.ViewUtils;

/**
 * @author stone
 */

public class SplashActivity extends BaseActivity implements ISplashView,
        MediaPlayer.OnCompletionListener, Animation.AnimationListener, View.OnClickListener, View.OnTouchListener {

    private ImageView mSplashIv;
    private VideoView mSplashVv;
    private ISplashPresenter mSplashPresenter;
    private AnimationSet mAnimationSet;
    private RelativeLayout mSplashRl;
    private boolean mIsNetworked = true;
    private boolean mIsAnimationed = false;
    private TextView mSplashTv;
    private GestureDetector mGesture;

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        super.initView();
        mSplashIv = (ImageView) findViewById(R.id.splash_iv);
        mSplashVv = (VideoView) findViewById(R.id.splash_vv);
        mSplashRl = (RelativeLayout) findViewById(R.id.splash_rl);
        mSplashTv = (TextView) findViewById(R.id.splash_tv);
    }

    @Override
    protected void initData() {
        super.initData();
        mSplashRl.setBackgroundColor(ViewUtils.getColor(R.color.app_red));
        mSplashPresenter = new SplashPresenter(this);
    }

    @Override
    protected Subscription initNetwork() {
        mIsNetworked = false;
        return mSplashPresenter.getNetworkSubscription();
    }

    @Override
    public void showNetworkError() {
        ToastUtils.showShortToast("网络连接失败，请检查一下网络", mPath);
    }

    @Override
    public void doNetworkFinish() {
        mIsNetworked = true;
        if (mIsAnimationed) {
            mSplashPresenter.startActivityOrVideo();
        }
    }

    @Override
    protected void initAnimation() {
        mAnimationSet = new AnimationSet(true);
        mAnimationSet.addAnimation(new AlphaAnimation(0f, 1f));
        mAnimationSet.addAnimation(new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f));
        mAnimationSet.addAnimation(new ScaleAnimation(0.2f, 1, 0.2f, 1, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f));
        mAnimationSet.setDuration(5000);
        mSplashIv.setAnimation(mAnimationSet);
    }

    @Override
    protected void initListener() {
        mAnimationSet.setAnimationListener(this);
        mSplashTv.setOnClickListener(this);
        mSplashVv.setOnTouchListener(this);
        mSplashVv.setOnCompletionListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.splash_tv:
                mSplashVv.pause();
                mSplashRl.setBackgroundColor(ViewUtils.getColor(R.color.app_red));
                doStartActivity();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mGesture == null) {
            mGesture = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDown(MotionEvent e) {
                    if (mSplashTv.getVisibility() == View.GONE) {
                        mSplashTv.setVisibility(View.VISIBLE);
                    } else {
                        mSplashTv.setVisibility(View.GONE);
                    }
                    return true;
                }
            });
        }
        return mGesture.onTouchEvent(event);
    }

    /**
     * 动画结束的回调事件
     */
    @Override
    public void onAnimationEnd(Animation animation) {
        mIsAnimationed = true;
        if (mIsNetworked) {
            mSplashPresenter.startActivityOrVideo();
        }
    }

    @Override
    public void doStartActivity() {
        IntentUtils.startActivity(SplashActivity.this, MainActivity.class, true);
    }

    @Override
    public void doStartVideo() {
        mSplashIv.setVisibility(View.GONE);
        mSplashRl.setBackgroundColor(Color.BLACK);
        mSplashVv.setVisibility(View.VISIBLE);
        MediaController mediaController = new MediaController(SplashActivity.this);
        mediaController.setVisibility(View.GONE);
        mSplashVv.setMediaController(mediaController);
        mSplashVv.setVideoURI(Uri.parse("android.resource://" + ViewUtils.getContext().getPackageName() + "/" + R.raw.welcome));
        mSplashVv.start();
    }

    /**
     * 视频播放结束的回调事件
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
        mSplashRl.setBackgroundColor(ViewUtils.getColor(R.color.app_red));
        doStartActivity();
    }

    /**
     * 动画开始的回调事件
     */
    @Override
    public void onAnimationStart(Animation animation) {
    }

    /**
     * 动画重复的回调事件
     */
    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
