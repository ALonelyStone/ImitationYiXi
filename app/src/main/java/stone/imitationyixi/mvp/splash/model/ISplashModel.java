package stone.imitationyixi.mvp.splash.model;

import rx.Subscription;

/**
 * @author stone
 */

public interface ISplashModel {

    /**
     * 进行网络请求和数据库操作<p>
     * （获取讲座列表并保存到数据库）
     */
    Subscription networkAndDatabase();

    /**
     * 进行判断是进行活动跳转还是视频播放
     */
    void startActivityOrVideo();

    interface ISplashListener {

        /**
         * 网络请求错误的回调方法
         */
        void onNetworkError();

        /**
         * 网络请求完成的回调方法
         */
        void onNetworkFinish();

        /**
         * 开始活动跳转的回调方法
         */
        void onStartActivity();

        /**
         * 开始视频播放的回调方法
         */
        void onStartVideo();
    }

}
