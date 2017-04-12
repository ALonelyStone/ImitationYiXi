package stone.imitationyixi.mvp.splash.presenter;

import rx.Subscription;

/**
 * @author stone
 */

public interface ISplashPresenter {

    /**
     * 进行网络请求
     */
    Subscription getNetworkSubscription();

    /**
     * 进行判断是进行活动跳转还是视频播放
     */
    void startActivityOrVideo();

}
