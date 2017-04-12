package stone.imitationyixi.mvp.lecture.model;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.CommentsBean;
import stone.imitationyixi.bean.LectureBean;
import stone.imitationyixi.bean.RelatedBean;

/**
 * @author Kotori
 * @time 2017/3/7  13:16
 * @desc ${TODD}
 */

public interface ILectureModel {
    /**
     * 进行网络请求和数据库操作<p>
     */
    Subscription networkAndDatabase(String id);

    /**
     * 进行小评论的网络请求
     */
    void networkToSmallComments(String lecturerId);

    void networkToBottom(int lectureId);

    interface ILectureListener {

        /**
         * 网络请求错误的回调方法
         */
        void onNetworkError();

        /**
         * 网络请求完成的回调方法
         */
        void onNetworkFinish(LectureBean bean);

        /**
         * 网络请求完成的回调方法
         */
        void onNetworkSmallCommentsFinish(List<CommentsBean> bean);

        void onNetworkBottomFinish(List<RelatedBean> relatedBeanList);
    }
}
