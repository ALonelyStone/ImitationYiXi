package stone.imitationyixi.mvp.login.presenter;


import stone.imitationyixi.bean.LonginAndRegistBean;

/**
 * Created by Administrator on 2017/3/7.
 */

public interface IRegistPersenter {
    void registUser(String mail, String pwd);//注册用户

    void registError(String message);

    void registResult(LonginAndRegistBean bean);
}

