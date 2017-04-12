package stone.imitationyixi.mvp.main.model;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import stone.imitationyixi.ImitationYiXiApplication;
import stone.imitationyixi.bean.UserBean;
import stone.imitationyixi.utils.ViewUtils;

/**
 * @author stone
 */

public class MainModel implements IMainModel {

    private final IMainListener mListener;

    public MainModel(IMainListener listener) {
        mListener = listener;
    }

    @Override
    public Subscription getNetworkSubscription() {
        final UserBean userBean = ImitationYiXiApplication.getUserInfo();
        return Observable
                .create(new Observable.OnSubscribe<GlideDrawable>() {
                    @Override
                    public void call(final Subscriber<? super GlideDrawable> subscriber) {
                        try {
                            subscriber.onNext(Glide
                                    .with(ViewUtils.getContext())
                                    .load(userBean.getPic())
                                    .bitmapTransform(new CropCircleTransformation(new BitmapPoolAdapter()))
                                    .into(80, 80)
                                    .get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GlideDrawable>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mListener.onNetworkError();
                    }

                    @Override
                    public void onNext(GlideDrawable drawable) {
                        mListener.onNetworkSuccess(drawable);
                    }
                });
    }
}
