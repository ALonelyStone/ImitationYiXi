package stone.imitationyixi.mvp.base.model;

/**
 * @author stone
 */

public interface IBaseModel {

    void getSettingTypeface();

    interface IBaseListener{
        void onTypefaceGet(String typeface);
    }

}
