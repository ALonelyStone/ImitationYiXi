package stone.imitationyixi.mvp.setting.view;

/**
 * @author stone
 */

public interface ISettingView {

    /**
     * 获取缓存大小
     */
    void getCacheSize();

    /**
     * 设置显示的缓存大小
     */
    void setCacheSize(String size);

    /**
     * 显示加载提示
     */
    void showLoading();

    /**
     * 隐藏加载提示
     */
    void hideLoading();

}
