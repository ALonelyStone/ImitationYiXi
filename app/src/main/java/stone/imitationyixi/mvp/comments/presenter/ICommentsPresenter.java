package stone.imitationyixi.mvp.comments.presenter;

import rx.Subscription;

/**
 * @author Kotori
 * @time 2017/3/7  18:36
 * @desc ${TODD}
 */

public interface ICommentsPresenter  {

    Subscription getNetworkSubscription(int lectureId, int page);

    void getNetworkForLoadMore(int lectureId, int page);


}
