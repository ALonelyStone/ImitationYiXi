package stone.imitationyixi.mvp.main.presenter;

import rx.Subscription;

/**
 * @author stone
 */

public interface IMainPresenter {

    Subscription getNetworkSubscription();
}
