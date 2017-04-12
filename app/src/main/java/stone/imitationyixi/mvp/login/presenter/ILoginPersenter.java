package stone.imitationyixi.mvp.login.presenter;

import stone.imitationyixi.bean.LonginAndRegistBean;

/**
 * Created by Administrator on 2017/3/7.
 */

public interface ILoginPersenter {
    void requestUserInfo(String uid);

    void loginFail(LonginAndRegistBean bean);

    void loginError(String e);

    void loginSuccessful();

    void toLogin(String mail, String pwd);
}
