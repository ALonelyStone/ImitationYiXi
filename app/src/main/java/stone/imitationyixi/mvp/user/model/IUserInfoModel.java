package stone.imitationyixi.mvp.user.model;

import rx.Subscription;

/**
 * Created by Administrator on 2017/3/8.
 */

public interface IUserInfoModel {
    Subscription getIDList(int id);
}
