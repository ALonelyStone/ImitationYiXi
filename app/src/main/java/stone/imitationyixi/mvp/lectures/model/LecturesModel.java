package stone.imitationyixi.mvp.lectures.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import stone.imitationyixi.bean.BaseListBean;
import stone.imitationyixi.bean.SeminarBean;
import stone.imitationyixi.common.NetworkConstant;
import stone.imitationyixi.database.LectureListDao;
import stone.imitationyixi.utils.JsonUtils;
import stone.imitationyixi.utils.NetworkUtils;
import stone.imitationyixi.utils.ViewUtils;

import static stone.imitationyixi.database.LectureListDao.OrderBy.likeNumAsc;
import static stone.imitationyixi.database.LectureListDao.OrderBy.likeNumDesc;
import static stone.imitationyixi.database.LectureListDao.OrderBy.timeAsc;
import static stone.imitationyixi.database.LectureListDao.OrderBy.timeDesc;
import static stone.imitationyixi.database.LectureListDao.OrderBy.viewNumAsc;
import static stone.imitationyixi.database.LectureListDao.OrderBy.viewNumDesc;

public class LecturesModel implements ILecturesModel {


    private ILecturesModelListener mListener;
    LectureListDao mDao = null;

    public LecturesModel(Context context, ILecturesModelListener listener) {
        mListener = listener;
        mDao = new LectureListDao(context);
    }

    @Override
    public void getIndroduceLectures(final boolean ifAscending, final int i) {
        Observable
                .create(new Observable.OnSubscribe<List<Map<String, String>>>() {
                    @Override
                    public void call(final Subscriber<? super List<Map<String, String>>> subscriber) {
                        LectureListDao.OrderBy orderIndex = timeDesc;
                        orderIndex = getOrderBy(orderIndex, ifAscending, i);
                        List<Map<String, String>> data = mDao.loadLectureList(orderIndex);
                        subscriber.onNext(data);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Map<String, String>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.toString());
                    }

                    @Override
                    public void onNext(List<Map<String, String>> result) {
                        mListener.onLecturesListDataFinished(result);
                    }
                });

    }

    private LectureListDao.OrderBy getOrderBy(LectureListDao.OrderBy orderIndex, boolean ifAscending, int i) {
        if (ifAscending) {
            if (i == 1) {
                orderIndex = timeDesc;
            } else if (i == 2) {
                orderIndex = viewNumDesc;
            } else if (i == 3) {
                orderIndex = likeNumDesc;
            }
        } else {
            if (i == 1) {
                orderIndex = timeAsc;
            } else if (i == 2) {
                orderIndex = viewNumAsc;
            } else if (i == 3) {
                orderIndex = likeNumAsc;
            }
        }
        return orderIndex;
    }

    public void getBanner() {
        Observable
                .create(new Observable.OnSubscribe<List<Map<String, String>>>() {
                    @Override
                    public void call(final Subscriber<? super List<Map<String, String>>> subscriber) {
                        LectureListDao.OrderBy orderIndex = timeDesc;
                        List<Map<String, String>> data = mDao.loadLectureList(orderIndex);
                        List<Map<String, String>> subData = data.subList(0, 5);
                        subscriber.onNext(subData);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Map<String, String>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.toString());
                    }

                    @Override
                    public void onNext(List<Map<String, String>> result) {
                        mListener.onBannerCompleteListener(result);
                    }
                });
    }

    @Override
    public void refreshListView() {
        Observable
                .create(new Observable.OnSubscribe<Subscription>() {
                    @Override
                    public void call(final Subscriber<? super Subscription> subscriber) {
                        Subscription subscription = networkAndDatabase();
                        subscriber.onNext(subscription);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Subscription>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.toString());
                    }
                    @Override
                    public void onNext(Subscription result) {
                        mListener.onRefrshListViewComplete(result);
                    }
                });
    }

    public Subscription networkAndDatabase() {
        return NetworkUtils.doGet(NetworkConstant.getAlbumUrl(), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                Type type = new TypeToken<BaseListBean<SeminarBean>>() {
                }.getType();
                BaseListBean<SeminarBean> baseListBean = JsonUtils.parseJson(result, type);
                List<SeminarBean> seminarBeen = baseListBean.getData();
                LectureListDao lectureListDao = new LectureListDao(ViewUtils.getContext());
                lectureListDao.clearAll();
                for (SeminarBean seminarBean : seminarBeen) {
                    List<SeminarBean.LecturesBean> lecturesBeen = seminarBean.getLectures();
                    for (SeminarBean.LecturesBean lecturesBean : lecturesBeen) {
                        lectureListDao.saveLectureList(lecturesBean);
                    }
                }
            }
            @Override
            public void onFail(Throwable e) {
            }
            @Override
            public void onFinally() {
            }
        });
    }
}
