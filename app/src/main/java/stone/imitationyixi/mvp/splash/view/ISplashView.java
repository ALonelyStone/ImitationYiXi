package stone.imitationyixi.mvp.splash.view;

/**
 * @author stone
 */

public interface ISplashView {

    /**
     * 显示网络错误提示
     */
    void showNetworkError();

    /**
     * 进行网络请求完成后的操作
     */
    void doNetworkFinish();

    /**
     * 进行活动跳转
     */
    void doStartActivity();

    /**
     * 进行视频播放
     */
    void doStartVideo();

}
