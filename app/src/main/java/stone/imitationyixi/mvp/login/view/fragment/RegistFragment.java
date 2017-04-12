package stone.imitationyixi.mvp.login.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import stone.imitationyixi.mvp.login.presenter.IRegistPersenter;
import stone.imitationyixi.mvp.login.presenter.RegistPersenter;
import stone.imitationyixi.mvp.login.view.activity.LoginActivity;
import stone.imitationyixi.utils.ViewUtils;


/**
 * Created by Administrator on 2017/3/7.
 */

public class RegistFragment extends BaseFragment implements View.OnClickListener, IRegistView, TextWatcher {

    private IRegistPersenter mPersenter;
    private LoginActivity mActivity;
    private InputMethodManager mImm;
    private TextInputLayout pwdEtl;
    private TextInputLayout mailEt;
    private String mMail;
    private String mPwd;
    private Button mOkBtn;
    private EditText mMail_et;
    private EditText mPwd_et;

    private int btnColor = 0xffCC2129;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (LoginActivity) getActivity();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.regist_frag, null, false);
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
        mPersenter = new RegistPersenter(this, getActivity());
    }

    @Override
    public void onClick(View v) {
        mMail = mailEt.getEditText().getText().toString();
        mPwd = pwdEtl.getEditText().getText().toString();
        if (mailEt.isFocused()) mailEt.clearFocus();//取消输入
        if (pwdEtl.isFocused()) pwdEtl.clearFocus();
        mImm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//隐藏输入法

        mOkBtn.setText(ViewUtils.getString(R.string.later));
        mOkBtn.setBackgroundColor(Color.GRAY);
        mOkBtn.setClickable(false);

        mPersenter.registUser(mMail, mPwd);
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
    public void registError(String message) {
        mOkBtn.setText(ViewUtils.getString(R.string.submit));
        mOkBtn.setBackgroundColor(btnColor);
        mOkBtn.setClickable(true);
        showToast("网络异常,检查网络状态");
    }

    /**
     * 注册失败
     *
     * @param msg
     */
    @Override
    public void showToast(String msg) {
        mActivity.showToast(msg);
    }

    /**
     * 注册成功
     *
     * @param uid
     */
    @Override
    public void registSuccessful(String uid) {
        mOkBtn.setText(ViewUtils.getString(R.string.submit));
        mOkBtn.setBackgroundColor(btnColor);
        mOkBtn.setClickable(true);
        mActivity.toLogin(uid);
    }

    //以下是edittext文本改变监听
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
