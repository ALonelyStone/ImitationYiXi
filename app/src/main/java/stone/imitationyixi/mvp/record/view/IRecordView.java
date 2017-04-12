package stone.imitationyixi.mvp.record.view;

import java.util.List;

import stone.imitationyixi.bean.RecordBean;

/**
 * @author stone
 */

public interface IRecordView {

    void hideLoading();

    void showNetworkError();

    void doNetworkSuccess(List<RecordBean> recordBeen);

}
