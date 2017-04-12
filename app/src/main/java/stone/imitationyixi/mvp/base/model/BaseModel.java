package stone.imitationyixi.mvp.base.model;

import android.text.TextUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import stone.imitationyixi.R;
import stone.imitationyixi.common.SharedPreferenceConstant;
import stone.imitationyixi.utils.SharedPreferenceUtils;
import stone.imitationyixi.utils.ViewUtils;

/**
 * @author stone
 */

public class BaseModel implements IBaseModel {


    private final IBaseListener mListener;

    public BaseModel(IBaseListener listener) {
        mListener = listener;
    }

    @Override
    public void getSettingTypeface() {
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        String typeface = SharedPreferenceUtils.getString(ViewUtils.getContext(), SharedPreferenceConstant.TYPEFACE);
                        if (TextUtils.isEmpty(typeface)) {
                            String[] typefaces = ViewUtils.getResources().getStringArray(R.array.typeface_url);
                            SharedPreferenceUtils.putString(ViewUtils.getContext(), SharedPreferenceConstant.TYPEFACE, typefaces[0]);
                            subscriber.onNext(typefaces[0]);
                        } else {
                            subscriber.onNext(typeface);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(String typeface) {
                        System.out.println("onNext -- System.out = ");
                        mListener.onTypefaceGet(typeface);
                    }
                });
    }


}
