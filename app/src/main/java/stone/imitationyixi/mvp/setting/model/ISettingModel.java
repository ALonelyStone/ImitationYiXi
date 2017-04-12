package stone.imitationyixi.mvp.setting.model;

/**
 * @author stone
 */

public interface ISettingModel {

    void getCacheSize();

    void cleanCache();

    interface ISettingListener{
        void onCacheSizeGet(String size);
        void onCleaned();
    }

}
