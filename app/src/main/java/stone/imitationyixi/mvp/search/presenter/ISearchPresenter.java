package stone.imitationyixi.mvp.search.presenter;

import rx.Subscription;

/**
 * @author stone
 */

public interface ISearchPresenter {

    /**
     * 返回搜索的网络请求
     */
    Subscription getSearchNetworkSubscription(String searchText);

}
