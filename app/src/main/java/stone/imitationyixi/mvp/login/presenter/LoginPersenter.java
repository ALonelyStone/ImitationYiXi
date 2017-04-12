package stone.imitationyixi.mvp.login.presenter;

import android.text.TextUtils;

import stone.imitationyixi.bean.LonginAndRegistBean;
import stone.imitationyixi.mvp.login.model.LoginModel;
import stone.imitationyixi.mvp.login.view.fragment.ILoginView;

/**
 * Created by Administrator on 2017/3/7.
 */
public class LoginPersenter implements ILoginPersenter {

    private final ILoginView mView;
    private final LoginModel mModel;

    public LoginPersenter(ILoginView view) {
        mModel = new LoginModel(this);
        mView = view;
    }


    /**
     * 登录判断
     *
     * @param mail
     * @param pwd
     */
    @Override
    public void toLogin(String mail, String pwd) {
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

        mModel.toLogin(mail, pwd);
    }


    /**
     * 登录失败回调
     *
     * @param bean
     */
    @Override
    public void loginFail(LonginAndRegistBean bean) {
        mView.loginError("帐号不存在或不正确,请重新输入");
    }

    /**
     * 网络异常回调
     *
     * @param message
     */
    @Override
    public void loginError(String message) {
        mView.loginError("网络异常,检查网络状态");
    }

    /**
     * 登录成功回调
     */
    @Override
    public void loginSuccessful() {
        mView.loginSuccessful();
    }

    /**
     * 注册后的获取用户信息过程
     *
     * @param uid
     */
    @Override
    public void requestUserInfo(String uid) {
        mModel.requestUserInfo(uid);
    }
}
