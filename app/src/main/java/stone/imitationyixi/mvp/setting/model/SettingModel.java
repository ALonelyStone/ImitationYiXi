package stone.imitationyixi.mvp.setting.model;

import android.os.Environment;

import java.io.File;
import java.text.DecimalFormat;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import stone.imitationyixi.utils.DataCleanUtils;
import stone.imitationyixi.utils.ViewUtils;

/**
 * @author stone
 */

public class SettingModel implements ISettingModel {

    private final ISettingListener mListener;

    public SettingModel(ISettingListener listener) {
        mListener = listener;
    }

    @Override
    public void getCacheSize() {
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        try {
                            long internalCacheSize = DataCleanUtils.getFolderSize(ViewUtils.getContext().getCacheDir());
                            long databasesSize = DataCleanUtils.getFolderSize(new File("/data/data/"
                                    + ViewUtils.getContext().getPackageName() + "/databases"));
                            long externalCacheSize = 0L;
                            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                                externalCacheSize = DataCleanUtils.getFolderSize(ViewUtils.getContext().getExternalCacheDir());
                            }
                            float cacheSizeF = internalCacheSize + databasesSize + externalCacheSize;
                            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                            String cacheSizeStr = "0.0 B";
                            if (cacheSizeF < 1024f) {
                                cacheSizeStr = String.valueOf(decimalFormat.format(cacheSizeF) + " B");
                            } else if (cacheSizeF < 1024f * 1024f) {
                                cacheSizeStr = String.valueOf(decimalFormat.format(cacheSizeF / 1024f) + " KB");
                            } else if (cacheSizeF < 1024f * 1024f * 1024f) {
                                cacheSizeStr = String.valueOf(decimalFormat.format(cacheSizeF / 1024f / 1024f) + " MB");
                            } else if (cacheSizeF < 1024f * 1024f * 1024f * 1024f) {
                                cacheSizeStr = String.valueOf(decimalFormat.format(cacheSizeF / 1024f / 1024f / 1024f) + " GB");
                            }
                            subscriber.onNext(cacheSizeStr);
                        } catch (Exception e) {
                            e.printStackTrace();
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
                    public void onNext(String cacheSize) {
                        mListener.onCacheSizeGet(cacheSize);
                    }
                });
    }

    @Override
    public void cleanCache() {
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        DataCleanUtils.cleanInternalCache();
                        DataCleanUtils.cleanDatabases();
                        DataCleanUtils.cleanExternalCache();
                        subscriber.onNext("");
                        subscriber.onCompleted();
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
                    public void onNext(String cacheSize) {
                        mListener.onCleaned();
                    }
                });
    }
}
