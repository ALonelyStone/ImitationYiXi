package stone.imitationyixi.mvp.comments.view;

import java.util.List;

import stone.imitationyixi.bean.CommentsBean;
import stone.imitationyixi.bean.UserBean;

/**
 * @author stone
 */

public interface ICommentsView {

    /**
     * 显示网络错误提示
     */
    void showNetworkError();

    /**
     * 进行网络请求完成后的操作
     */
    void doNetworkFinish(List<CommentsBean> commentsBeen);

    void doNetworkForLoadMoreFinish(List<CommentsBean> commentsBeen);

}
