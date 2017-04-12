package stone.imitationyixi.mvp.splash.presenter;

import rx.Subscription;
import stone.imitationyixi.mvp.splash.model.ISplashModel;
import stone.imitationyixi.mvp.splash.model.SplashModel;
import stone.imitationyixi.mvp.splash.view.ISplashView;

/**
 * @author stone
 */

public class SplashPresenter implements ISplashPresenter, ISplashModel.ISplashListener {

    private final ISplashModel mSplashModel;
    private final ISplashView mSplashView;

    public SplashPresenter(ISplashView splashView) {
        mSplashModel = new SplashModel(this);
        mSplashView = splashView;
    }

    @Override
    public Subscription getNetworkSubscription() {
        return mSplashModel.networkAndDatabase();
    }

    @Override
    public void startActivityOrVideo() {
        mSplashModel.startActivityOrVideo();
    }

    @Override
    public void onNetworkError() {
        mSplashView.showNetworkError();
    }

    @Override
    public void onNetworkFinish() {
        mSplashView.doNetworkFinish();
    }

    @Override
    public void onStartActivity() {
        mSplashView.doStartActivity();
    }

    @Override
    public void onStartVideo() {
        mSplashView.doStartVideo();
    }
}
