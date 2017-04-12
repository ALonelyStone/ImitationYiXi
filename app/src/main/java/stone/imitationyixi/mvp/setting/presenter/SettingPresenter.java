package stone.imitationyixi.mvp.setting.presenter;

import stone.imitationyixi.mvp.setting.model.ISettingModel;
import stone.imitationyixi.mvp.setting.model.SettingModel;
import stone.imitationyixi.mvp.setting.view.ISettingView;

/**
 * @author stone
 */

public class SettingPresenter implements ISettingPresenter, ISettingModel.ISettingListener {

    private final ISettingView mSettingView;
    private final ISettingModel mSettingModel;

    public SettingPresenter(ISettingView settingView) {
        mSettingModel = new SettingModel(this);
        mSettingView = settingView;
    }

    @Override
    public void getCacheSize() {
        mSettingView.showLoading();
        mSettingModel.getCacheSize();
    }

    @Override
    public void cleanCache() {
        mSettingView.showLoading();
        mSettingModel.cleanCache();
    }

    @Override
    public void onCacheSizeGet(String size) {
        mSettingView.hideLoading();
        mSettingView.setCacheSize(size);
    }

    @Override
    public void onCleaned() {
        mSettingView.getCacheSize();
    }
}
