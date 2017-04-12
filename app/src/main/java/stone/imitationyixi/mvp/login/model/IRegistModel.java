package stone.imitationyixi.mvp.login.model;

import rx.Subscription;

/**
 * Created by Administrator on 2017/3/7.
 */
public interface IRegistModel {
    Subscription registUser(String mail, String pwd);
}
