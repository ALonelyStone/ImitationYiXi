package stone.imitationyixi.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author stone
 *         网络工具类
 */

public class NetworkUtils {

    private static OkHttpClient sOkHttpClient;

    /**
     * 获取OkHttpClient对象（单例模式）
     */
    private static OkHttpClient getInstance() {
        if (sOkHttpClient == null) {
            synchronized (NetworkUtils.class) {
                if (sOkHttpClient == null) {
                    sOkHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return sOkHttpClient;
    }

    /**
     * 进行Get网络请求
     *
     * @param url      请求地址
     * @param callBack 回调方法
     */
    public static Subscription doGet(String url, NetworkStringCallBack callBack) {
        Request request = new Request.Builder().url(url).build();
        return requestGetStringResult(request, callBack);
    }

    /**
     * 进行Post网络请求
     *
     * @param url       请求地址
     * @param paramsMap Post参数
     * @param callBack  回调方法
     */
    public static Subscription doPost(String url, HashMap<String, String> paramsMap, NetworkStringCallBack callBack) {
        FormBody formBody = buildParams(paramsMap);
        Request request = new Request.Builder().url(url).post(formBody).build();
        return requestGetStringResult(request, callBack);
    }

    /**
     * 组装Post参数
     */
    private static FormBody buildParams(HashMap<String, String> paramsMap) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    /**
     * 网络请求
     * (采用RxJava进行异步操作)
     *
     * @param callBack 回调方法
     */
    private static Subscription requestGetStringResult(final Request request, final NetworkStringCallBack callBack) {
        return Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(final Subscriber<? super String> subscriber) {
                        getInstance();
                        //调用请求方法
                        sOkHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                                subscriber.onError(e);
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                subscriber.onNext(response.body().string());
                                subscriber.onCompleted();
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        callBack.onFinally();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        callBack.onFail(e);
                        callBack.onFinally();
                    }

                    @Override
                    public void onNext(String result) {
                        callBack.onSuccessResponse(result);
                    }
                });
    }

    public interface NetworkStringCallBack {
        /**
         * 成功，回调请求到的字符串
         */
        void onSuccessResponse(String result);

        /**
         * 失败，回调出现的异常
         */
        void onFail(Throwable e);

        /**
         * 不管成功与否都会回调的方法
         */
        void onFinally();
    }

}
