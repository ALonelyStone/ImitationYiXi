package stone.imitationyixi.mvp.comments.model;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.CommentsBean;

/**
 * @author Kotori
 * @time 2017/3/7  18:38
 * @desc ${TODD}
 */

public interface ICommentsModel {

    Subscription networkAndDatabase(int lectureId, int page);

    void networkForLoadMore(int lectureId, int page);


    interface ICommentsListener {

        void onNetworkError();

        void onNetworkFinish(List<CommentsBean> commentsBeen);

        void onNetworkForLoadMoreFinish(List<CommentsBean> commentsBeen);

    }
}
