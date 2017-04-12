package stone.imitationyixi.mvp.login.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import stone.imitationyixi.R;
import stone.imitationyixi.base.BaseFragment;
import stone.imitationyixi.mvp.login.presenter.ILoginPersenter;
import stone.imitationyixi.mvp.login.presenter.LoginPersenter;
import stone.imitationyixi.mvp.login.view.activity.LoginActivity;
import stone.imitationyixi.mvp.user.view.activity.UserInfoActivity;
import stone.imitationyixi.utils.IntentUtils;
import stone.imitationyixi.utils.ViewUtils;

/**
 * Created by Administrator on 2017/3/7.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener, TextWatcher, ILoginView {

    private LoginActivity mActivity;
    private InputMethodManager mImm;
    private TextInputLayout pwdEtl;
    private TextInputLayout mailEt;
    private String mMail;
    private String mPwd;
    private Button mOkBtn;
    private EditText mMail_et;
    private EditText mPwd_et;
    private ILoginPersenter mLoginPersenter;

    private int btnColor = 0xffCC2129;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_frag, null, false);
        mOkBtn = (Button) view.findViewById(R.id.ok_btn);
        mailEt = (TextInputLayout) view.findViewById(R.id.mail_etl);
        pwdEtl = (TextInputLayout) view.findViewById(R.id.pwd_etl);
        mMail_et = (EditText) view.findViewById(R.id.email_et);
        mPwd_et = (EditText) view.findViewById(R.id.pwd_et);
        pwdEtl.setCounterEnabled(true);//显示密码输入字数

        //获得输入法服务
        mImm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        return view;
    }

    @Override
    protected void initListener() {
        mOkBtn.setOnClickListener(this);
        mMail_et.addTextChangedListener(this);//监听输入框改变状态
        mPwd_et.addTextChangedListener(this);//监听输入框改变状态
    }

    @Override
    protected void initData() {
        super.initData();
        mLoginPersenter = new LoginPersenter(this);
        mActivity = (LoginActivity) getActivity();
    }

    @Override
    public void onClick(View v) {
        mMail = mailEt.getEditText().getText().toString();
        mPwd = pwdEtl.getEditText().getText().toString();
        if (mailEt.isFocused()) mailEt.clearFocus();//取消输入框焦点
        if (pwdEtl.isFocused()) pwdEtl.clearFocus();
        mImm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//隐藏输入法

        mOkBtn.setText(ViewUtils.getString(R.string.later));
        mOkBtn.setBackgroundColor(Color.GRAY);
        mOkBtn.setClickable(false);

        mLoginPersenter.toLogin(mMail, mPwd);
    }

    /**
     * 注册成功调用的登录入口
     *
     * @param uid
     */
    public void toLogin(String uid) {
        mLoginPersenter.requestUserInfo(uid);
    }

    /**
     * 邮箱错误
     *
     * @param mail
     */
    @Override
    public void mailError(String mail) {
        mOkBtn.setText(ViewUtils.getString(R.string.submit));
        mOkBtn.setBackgroundColor(btnColor);
        mOkBtn.setClickable(true);
        mailEt.setError(mail);
    }

    /**
     * 密码错误
     *
     * @param pwd
     */
    @Override
    public void pwdError(String pwd) {
        mOkBtn.setText(ViewUtils.getString(R.string.submit));
        mOkBtn.setBackgroundColor(btnColor);
        mOkBtn.setClickable(true);
        pwdEtl.setError(pwd);
    }

    /**
     * 网络异常
     *
     * @param message
     */
    @Override
    public void loginError(String message) {
        mOkBtn.setText(ViewUtils.getString(R.string.submit));
        mOkBtn.setBackgroundColor(btnColor);
        mOkBtn.setClickable(true);
        showToast(message);
    }

    /**
     * 登录成功
     */
    @Override
    public void loginSuccessful() {
        mOkBtn.setText(ViewUtils.getString(R.string.submit));
        mOkBtn.setBackgroundColor(btnColor);
        mOkBtn.setClickable(true);
        if (mActivity != null) {
            showToast("登录成功");
            IntentUtils.startActivity(mActivity, UserInfoActivity.class, true);
        }
    }

    /**
     * 根据参数弹出吐司
     *
     * @param msg
     */
    @Override
    public void showToast(String msg) {
        if (mActivity != null) {
            mActivity.showToast(msg);
        }
    }

    //输入框的改变状态
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mailEt.setError("");//取消输入错误信息
        pwdEtl.setError("");
    }
}
