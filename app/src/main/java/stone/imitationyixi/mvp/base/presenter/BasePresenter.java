package stone.imitationyixi.mvp.base.presenter;

import stone.imitationyixi.mvp.base.model.BaseModel;
import stone.imitationyixi.mvp.base.model.IBaseModel;
import stone.imitationyixi.mvp.base.model.IBaseModel.IBaseListener;
import stone.imitationyixi.mvp.base.view.IBaseView;

/**
 * @author stone
 */

public class BasePresenter implements IBasePresenter,IBaseListener {

    private final IBaseView mBaseView;
    private IBaseModel mBaseModel;

    public BasePresenter(IBaseView baseView){
        mBaseModel = new BaseModel(this);
        mBaseView = baseView;
    }

    @Override
    public void setTypeface() {
        mBaseModel.getSettingTypeface();
    }


    @Override
    public void onTypefaceGet(String typeface) {
        mBaseView.setTypeface(typeface);
    }
}
