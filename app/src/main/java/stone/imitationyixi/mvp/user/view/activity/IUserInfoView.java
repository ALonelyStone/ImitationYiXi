package stone.imitationyixi.mvp.user.view.activity;

import java.util.ArrayList;

import stone.imitationyixi.bean.LectureBean;

/**
 * Created by Administrator on 2017/3/8.
 */
public interface IUserInfoView {

    void returnCollList(ArrayList<LectureBean> beanlist);

    void returnDownList(ArrayList<LectureBean> beanlist);

    void returnReplyList(ArrayList<LectureBean> beanlist);
}
