package stone.imitationyixi.mvp.main.model;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;

import rx.Subscription;

/**
 * @author stone
 */

public interface IMainModel {

    Subscription getNetworkSubscription();

    interface IMainListener {

        void onNetworkError();

        void onNetworkSuccess(GlideDrawable drawable);


    }

}
