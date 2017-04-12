package stone.imitationyixi.mvp.login.view.activity;

import android.animation.Animator;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import stone.imitationyixi.R;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;
import stone.imitationyixi.mvp.login.view.fragment.LoginFragment;
import stone.imitationyixi.mvp.login.view.fragment.RegistFragment;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout loginLl;
    private Button login;
    private Button regist;
    private LoginFragment mLoginFragment;
    private RegistFragment mRegistFragment;
    private FrameLayout mFFrame;
    private TextView mLogin_btn;
    private TextView mRegist_btn;


    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        super.initView();
        loginLl = (LinearLayout) findViewById(R.id.login_ll);
        mLogin_btn = (TextView) findViewById(R.id.login);
        mLogin_btn.setOnClickListener(this);
        mRegist_btn = (TextView) findViewById(R.id.regist);
        mLogin_btn.setSelected(true);
        mRegist_btn.setOnClickListener(this);
        mFFrame = (FrameLayout) findViewById(R.id.login_panel);

        mLoginFragment = new LoginFragment();
        mRegistFragment = new RegistFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.login_panel, mLoginFragment, "login").add(R.id.login_panel, mRegistFragment, "regist").hide(mRegistFragment).commitAllowingStateLoss();
    }

    public void onClick(View v) {

        float maxRedius = getMaxRedius(mLogin_btn);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mLogin_btn.setSelected(false);
        mRegist_btn.setSelected(false);
        showFragmentAnim(mLogin_btn, maxRedius);
        showFragmentAnim(mRegist_btn, maxRedius);
        showFragmentAnim(mFFrame, getMaxRedius(mFFrame));
        switch (v.getId()) {
            case R.id.login://登录
                mLogin_btn.setSelected(true);
                transaction.show(mLoginFragment).hide(mRegistFragment).commitAllowingStateLoss();
                break;
            case R.id.regist://注册
                mRegist_btn.setSelected(true);
                transaction.show(mRegistFragment).hide(mLoginFragment).commitAllowingStateLoss();
                break;
        }
    }

    public int getMaxRedius(View view) {
        return view.getMeasuredWidth() > view.getMeasuredHeight() ? view.getMeasuredWidth() : view.getMeasuredHeight();
    }

    public void showFragmentAnim(View view, float maxRedius) {

        if (Build.VERSION.SDK_INT >= 21) {
            Animator animator = ViewAnimationUtils.createCircularReveal(view, view.getMeasuredWidth() / 2, view.getMeasuredHeight() / 2, 0, maxRedius);
            animator.setDuration(1000);
            animator.start();
        }
    }

    /**
     * 显示注册错误信息
     *
     * @param msg
     */
    public void showToast(String msg) {
        Snackbar.make(mFFrame, msg, Snackbar.LENGTH_LONG).show();
    }

    /**
     * 登录
     */
    public void toLogin(String uid) {
        mLoginFragment.toLogin(uid);
    }

    @Override
    protected boolean isHasBack() {
        return true;
    }

    @Override
    public boolean isHasMore() {
        return false;
    }
}


