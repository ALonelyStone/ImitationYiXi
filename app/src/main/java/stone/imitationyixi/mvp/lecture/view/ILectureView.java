package stone.imitationyixi.mvp.lecture.view;

import java.util.List;

import stone.imitationyixi.bean.CommentsBean;
import stone.imitationyixi.bean.LectureBean;
import stone.imitationyixi.bean.RelatedBean;

/**
 * @author stone
 */

public interface ILectureView {

    /**
     * 显示网络错误提示
     */
    void showNetworkError();

    /**
     * 进行网络请求完成后的操作
     */
    void doNetworkFinish(LectureBean bean);

    /**
     * 进行网络请求完成后的操作
     */
    void doNetworkSmallCommentsFinish(List<CommentsBean> bean);

    void doNetworkBottomFinish(List<RelatedBean> relatedBeanList);
}
