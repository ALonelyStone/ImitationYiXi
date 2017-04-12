package stone.imitationyixi.mvp.record.presenter;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.RecordBean;
import stone.imitationyixi.mvp.record.model.IRecordModel;
import stone.imitationyixi.mvp.record.model.RecordModel;
import stone.imitationyixi.mvp.record.view.IRecordView;

/**
 * @author stone
 */

public class RecordPresenter implements IRecordPresenter, IRecordModel.IRecordListener {

    private final IRecordView mRecordView;
    private final IRecordModel mRecordModel;

    public RecordPresenter(IRecordView recordView) {
        mRecordView = recordView;
        mRecordModel = new RecordModel(this);
    }

    @Override
    public Subscription getNetworkSubscription() {
        return mRecordModel.getNetworkSubscription();
    }

    @Override
    public void onNetworkError() {
        mRecordView.hideLoading();
        mRecordView.showNetworkError();
    }

    @Override
    public void onNetworkSuccess(List<RecordBean> recordBeen) {
        mRecordView.hideLoading();
        mRecordView.doNetworkSuccess(recordBeen);
    }
}
