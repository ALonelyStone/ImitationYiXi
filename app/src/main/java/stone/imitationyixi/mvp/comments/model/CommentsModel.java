package stone.imitationyixi.mvp.comments.model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.BaseListBean;
import stone.imitationyixi.bean.CommentsBean;
import stone.imitationyixi.common.NetworkConstant;
import stone.imitationyixi.utils.JsonUtils;
import stone.imitationyixi.utils.NetworkUtils;

/**
 * @author Kotori
 * @time 2017/3/7  18:37
 * @desc ${TODD}
 */

public class CommentsModel implements ICommentsModel {

    private  ICommentsListener mCommentsListener;

    public CommentsModel(ICommentsListener commentsListener) {
        mCommentsListener = commentsListener;
    }

    @Override
    public Subscription networkAndDatabase(int lecturerId,int page) {
        return NetworkUtils.doGet(NetworkConstant.getLectureCommentsUrl(String.valueOf(lecturerId),String.valueOf(page)), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                Type type = new TypeToken<BaseListBean<CommentsBean>>() {
                }.getType();
                BaseListBean<CommentsBean> comments = (BaseListBean<CommentsBean>) JsonUtils.parseJson(result, type);
                List<CommentsBean> commentsBeen = comments.getData();
                mCommentsListener.onNetworkFinish(commentsBeen);
            }

            @Override
            public void onFail(Throwable e) {
                mCommentsListener.onNetworkError();
            }

            @Override
            public void onFinally() {

            }
        });
    }

    @Override
    public void networkForLoadMore(int lecturerId,int page){
        NetworkUtils.doGet(NetworkConstant.getLectureCommentsUrl(String.valueOf(lecturerId),String.valueOf(page)), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                Type type = new TypeToken<BaseListBean<CommentsBean>>() {
                }.getType();
                BaseListBean<CommentsBean> comments = (BaseListBean<CommentsBean>) JsonUtils.parseJson(result, type);
                List<CommentsBean> commentsBeen = comments.getData();
                mCommentsListener.onNetworkForLoadMoreFinish(commentsBeen);
            }

            @Override
            public void onFail(Throwable e) {
                mCommentsListener.onNetworkError();
            }

            @Override
            public void onFinally() {

            }
        });
    }
}
