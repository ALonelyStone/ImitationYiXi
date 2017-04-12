package stone.imitationyixi.mvp.main.presenter;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;

import rx.Subscription;
import stone.imitationyixi.mvp.main.model.IMainModel;
import stone.imitationyixi.mvp.main.model.MainModel;
import stone.imitationyixi.mvp.main.view.IMainView;

/**
 * @author stone
 */

public class MainPresenter implements IMainPresenter, IMainModel.IMainListener {

    private final IMainView mMainView;
    private final IMainModel mMainModel;

    public MainPresenter(IMainView mainView) {
        mMainView = mainView;
        mMainModel = new MainModel(this);
    }

    @Override
    public Subscription getNetworkSubscription() {
        return mMainModel.getNetworkSubscription();
    }

    @Override
    public void onNetworkError() {
        mMainView.showNetworkError();
    }

    @Override
    public void onNetworkSuccess(GlideDrawable drawable) {
        mMainView.setNavigationIcon(drawable);
    }
}
