package stone.imitationyixi.mvp.record.presenter;

import rx.Subscription;

/**
 * @author stone
 */

public interface IRecordPresenter {

    Subscription getNetworkSubscription();

}
