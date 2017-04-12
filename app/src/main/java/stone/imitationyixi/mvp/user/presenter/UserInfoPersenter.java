package stone.imitationyixi.mvp.user.presenter;

import java.util.ArrayList;

import rx.Subscription;
import stone.imitationyixi.bean.LectureBean;
import stone.imitationyixi.mvp.user.model.IUserInfoModel;
import stone.imitationyixi.mvp.user.model.UserCollInfoModel;
import stone.imitationyixi.mvp.user.model.UserDownInfoModel;
import stone.imitationyixi.mvp.user.model.UserReplyInfoModel;
import stone.imitationyixi.mvp.user.view.activity.IUserInfoView;

/**
 * Created by Administrator on 2017/3/8.
 */
public class UserInfoPersenter implements IUserInfoPersenter {

    private final IUserInfoView mView;
    private final IUserInfoModel mCollModel;
    private final IUserInfoModel mDownModel;
    private final IUserInfoModel mReplyModel;

    public UserInfoPersenter(IUserInfoView view) {
        mView = view;
        mCollModel = new UserCollInfoModel(this);
        mDownModel = new UserDownInfoModel(this);
        mReplyModel = new UserReplyInfoModel(this);
    }

    @Override
    public Subscription getIDList(int id) {
        mCollModel.getIDList(id);
        mDownModel.getIDList(id);
        mReplyModel.getIDList(id);
        return null;

    }

    @Override
    public void returnCollList(ArrayList<LectureBean> beanlist) {
        mView.returnCollList(beanlist);
    }

    @Override
    public void returnDownList(ArrayList<LectureBean> beanlist) {
        mView.returnDownList(beanlist);
    }

    @Override
    public void returnReplyList(ArrayList<LectureBean> beanlist) {
        mView.returnReplyList(beanlist);
    }


}
