package stone.imitationyixi.mvp.lecture.model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.BaseBean;
import stone.imitationyixi.bean.BaseListBean;
import stone.imitationyixi.bean.CommentsBean;
import stone.imitationyixi.bean.LectureBean;
import stone.imitationyixi.bean.RelatedBean;
import stone.imitationyixi.common.NetworkConstant;
import stone.imitationyixi.utils.JsonUtils;
import stone.imitationyixi.utils.NetworkUtils;

/**
 * @author Kotori
 * @time 2017/3/7  13:15
 * @desc ${TODD}
 */

public class LectureModel implements ILectureModel {

    private ILectureListener mILectureListener;

    public LectureModel(ILectureListener lectureListener) {
        mILectureListener = lectureListener;
    }

    @Override
    public Subscription networkAndDatabase(String id) {
        return NetworkUtils.doGet(NetworkConstant.getLectureDetailUrl(id), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                Type type = new TypeToken<BaseBean<LectureBean>>() {
                }.getType();
                BaseBean<LectureBean> lectrue = (BaseBean<LectureBean>) JsonUtils.parseJson(result, type);
                LectureBean lectureBean = lectrue.getData();
                mILectureListener.onNetworkFinish(lectureBean);
            }

            @Override
            public void onFail(Throwable e) {
                mILectureListener.onNetworkError();
            }

            @Override
            public void onFinally() {

            }
        });
    }

    @Override
    public void networkToSmallComments(String lecturerId) {
        NetworkUtils.doGet(NetworkConstant.getLectureCommentsUrl(lecturerId, "1"), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                Type type = new TypeToken<BaseListBean<CommentsBean>>() {
                }.getType();
                BaseListBean<CommentsBean> commentsBean = JsonUtils.parseJson(result, type);
                List<CommentsBean> commentsBeen = commentsBean.getData();
                mILectureListener.onNetworkSmallCommentsFinish(commentsBeen);
            }

            @Override
            public void onFail(Throwable e) {
                mILectureListener.onNetworkError();
            }

            @Override
            public void onFinally() {
            }
        });
    }

    @Override
    public void networkToBottom(int lectureId) {
        NetworkUtils.doGet(NetworkConstant.getRelatedLecturesUrl(String.valueOf(lectureId)), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                Type type = new TypeToken<BaseListBean<RelatedBean>>() {
                }.getType();
                BaseListBean<RelatedBean> relatedBean = JsonUtils.parseJson(result, type);
                List<RelatedBean> relatedBeanList = relatedBean.getData();
                mILectureListener.onNetworkBottomFinish(relatedBeanList);
            }

            @Override
            public void onFail(Throwable e) {
                mILectureListener.onNetworkError();
            }

            @Override
            public void onFinally() {
            }
        });
    }
}