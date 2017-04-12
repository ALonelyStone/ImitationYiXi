package stone.imitationyixi.mvp.login.view.fragment;

/**
 * Created by Administrator on 2017/3/7.
 */

public interface BaseLoginView {
    void mailError(String mail);

    void pwdError(String pwd);



    void showToast(String s);

}
