package stone.imitationyixi.mvp.splash.model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.BaseListBean;
import stone.imitationyixi.bean.SeminarBean;
import stone.imitationyixi.common.NetworkConstant;
import stone.imitationyixi.common.SharedPreferenceConstant;
import stone.imitationyixi.database.LectureListDao;
import stone.imitationyixi.utils.JsonUtils;
import stone.imitationyixi.utils.NetworkUtils;
import stone.imitationyixi.utils.SharedPreferenceUtils;
import stone.imitationyixi.utils.ViewUtils;

/**
 * @author stone
 */

public class SplashModel implements ISplashModel {

    private final ISplashListener mListener;

    public SplashModel(ISplashListener listener) {
        mListener = listener;
    }

    @Override
    public Subscription networkAndDatabase() {
        long lecturesRefreshed = SharedPreferenceUtils.getLong(ViewUtils.getContext(), SharedPreferenceConstant.LECTURES_REFRESHED);
        if (lecturesRefreshed == 0 || System.currentTimeMillis() - lecturesRefreshed < 24 * 60 * 60 * 1000) {
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
                    mListener.onNetworkError();
                }

                @Override
                public void onFinally() {
                    mListener.onNetworkFinish();
                }
            });
        } else {
            mListener.onNetworkFinish();
            return null;
        }
    }

    @Override
    public void startActivityOrVideo() {
        Boolean launch = SharedPreferenceUtils.getBoolean(ViewUtils.getContext(), SharedPreferenceConstant.LAUNCH);
        if (!launch) {
            SharedPreferenceUtils.putBoolean(ViewUtils.getContext(), SharedPreferenceConstant.LAUNCH, true);
            mListener.onStartVideo();
        } else {
            mListener.onStartActivity();
        }
    }

}
