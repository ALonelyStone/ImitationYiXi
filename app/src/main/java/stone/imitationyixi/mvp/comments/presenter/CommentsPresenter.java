package stone.imitationyixi.mvp.comments.presenter;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.CommentsBean;
import stone.imitationyixi.mvp.comments.model.CommentsModel;
import stone.imitationyixi.mvp.comments.model.ICommentsModel;
import stone.imitationyixi.mvp.comments.view.ICommentsView;

/**
 * @author Kotori
 * @time 2017/3/7  18:36
 * @desc ${TODD}
 */

public class CommentsPresenter implements ICommentsPresenter,ICommentsModel.ICommentsListener {

    private ICommentsView mCommentsView;
    private ICommentsModel mCommentsModel;

    public CommentsPresenter(ICommentsView commentsView) {
        mCommentsView = commentsView;
        mCommentsModel = new CommentsModel(this);
    }

    @Override
    public Subscription getNetworkSubscription(int lectureId,int page) {
        return mCommentsModel.networkAndDatabase(lectureId,page);
    }

    @Override
    public void getNetworkForLoadMore(int lectureId, int page) {
       mCommentsModel.networkForLoadMore(lectureId, page);
    }

    @Override
    public void onNetworkError() {
        mCommentsView.showNetworkError();
    }

    @Override
    public void onNetworkFinish(List<CommentsBean> commentsBeen) {
        mCommentsView.doNetworkFinish(commentsBeen);
    }

    @Override
    public void onNetworkForLoadMoreFinish(List<CommentsBean> commentsBeen) {
        mCommentsView.doNetworkForLoadMoreFinish(commentsBeen);
    }

}
