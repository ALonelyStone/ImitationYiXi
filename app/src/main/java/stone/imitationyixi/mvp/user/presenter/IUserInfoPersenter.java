package stone.imitationyixi.mvp.user.presenter;

import java.util.ArrayList;

import rx.Subscription;
import stone.imitationyixi.bean.LectureBean;

/**
 * Created by Administrator on 2017/3/8.
 */

public interface IUserInfoPersenter {
    Subscription getIDList(int id);

    void returnCollList(ArrayList<LectureBean> beanlist);

    void returnDownList(ArrayList<LectureBean> beanlist);

    void returnReplyList(ArrayList<LectureBean> beanlist);
}
