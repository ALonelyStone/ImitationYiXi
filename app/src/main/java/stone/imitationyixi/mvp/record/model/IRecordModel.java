package stone.imitationyixi.mvp.record.model;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.RecordBean;

/**
 * @author stone
 */

public interface IRecordModel {

    Subscription getNetworkSubscription();


    interface IRecordListener {

        void onNetworkError();

        void onNetworkSuccess(List<RecordBean> recordBeen);
    }

}
