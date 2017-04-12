package stone.imitationyixi.mvp.user.model;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import stone.imitationyixi.ImitationYiXiApplication;
import stone.imitationyixi.bean.BaseBean;
import stone.imitationyixi.bean.LectureBean;
import stone.imitationyixi.common.NetworkConstant;
import stone.imitationyixi.database.UserDataDao;
import stone.imitationyixi.mvp.user.presenter.IUserInfoPersenter;
import stone.imitationyixi.utils.JsonUtils;
import stone.imitationyixi.utils.NetworkUtils;

/**
 * Created by Administrator on 2017/3/8.
 */

public class UserDownInfoModel implements IUserInfoModel {

    private final IUserInfoPersenter mPersenter;
    private UserDataDao mDao;

    public UserDownInfoModel(IUserInfoPersenter persenter) {
        mPersenter = persenter;
    }


    @Override
    public Subscription getIDList(int uid) {
        mDao = new UserDataDao(ImitationYiXiApplication.getContext());
//        savedata();
        Observable<List<Integer>> observable = findCollection();
        return observable.subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> list) {
                final ArrayList<LectureBean> beanlist = new ArrayList<>();
                for (Integer id : list) {
                    requestCollInfo(id, new UserNetWorkCallBack() {
                        @Override
                        public void onReturn(LectureBean bean) {
                            beanlist.add(bean);
                        }
                    });
                }
                mPersenter.returnDownList(beanlist);
            }
        });
    }

    private void requestCollInfo(int id, final UserNetWorkCallBack call) {
        NetworkUtils.doGet(NetworkConstant.getLectureDetailUrl(id + ""), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                BaseBean<LectureBean> baseBean = JsonUtils.parseJson(result, new TypeToken<BaseBean<LectureBean>>() {
                }.getType());
                LectureBean bean = baseBean.getData();
                call.onReturn(bean);
            }

            @Override
            public void onFail(Throwable e) {

            }

            @Override
            public void onFinally() {

            }
        });
    }

    private Observable<List<Integer>> findCollection() {
        Observable<List<Integer>> observable = Observable.create(new Observable.OnSubscribe<List<Integer>>() {
            @Override
            public void call(Subscriber<? super List<Integer>> subscriber) {
                List<Integer> list = mDao.findAllDownSpeech();
                subscriber.onNext(list);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
}
