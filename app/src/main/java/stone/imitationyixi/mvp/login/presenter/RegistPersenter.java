package stone.imitationyixi.mvp.login.presenter;

import android.app.Activity;
import android.text.TextUtils;

import stone.imitationyixi.bean.LonginAndRegistBean;
import stone.imitationyixi.mvp.login.model.IRegistModel;
import stone.imitationyixi.mvp.login.model.RegistModel;
import stone.imitationyixi.mvp.login.view.activity.LoginActivity;
import stone.imitationyixi.mvp.login.view.fragment.IRegistView;

/**
 * Created by Administrator on 2017/3/7.
 */

public class RegistPersenter implements IRegistPersenter {

    private final IRegistView mView;
    private final IRegistModel mRegistModel;
    private LoginActivity mActivity;


    public RegistPersenter(IRegistView registView, Activity activity) {
        mView = registView;
        mActivity = (LoginActivity) activity;
        mRegistModel = new RegistModel(this);
    }

    /**
     * 注册帐号
     *
     * @param mail
     * @param pwd
     */
    @Override
    public void registUser(String mail, String pwd) {
        if (TextUtils.isEmpty(mail)) {
            mView.mailError("请输入邮箱");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            mView.pwdError("请输入密码");
            return;
        }
        if (pwd.length() < 6) {
            mView.pwdError("密码不得少于6位数");
            return;
        }
        mRegistModel.registUser(mail, pwd);

    }

    /**
     * 网络异常
     *
     * @param message
     */
    @Override
    public void registError(String message) {
        mView.registError(message);
    }


    /**
     * 注册结果
     *
     * @param bean
     */
    @Override
    public void registResult(LonginAndRegistBean bean) {
        switch (bean.getRes()) {
            case 0://注册成功
                mView.registSuccessful(bean.getData());
                break;
            case 1://输入帐号有误
                mView.showToast("邮箱无法识别,请输入正确邮箱");
                break;
            case 2://用户已存在
                mView.showToast(bean.getMsg());
                break;
        }
    }
}
