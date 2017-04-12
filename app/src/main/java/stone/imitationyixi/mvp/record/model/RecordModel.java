package stone.imitationyixi.mvp.record.model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.BaseListBean;
import stone.imitationyixi.bean.RecordBean;
import stone.imitationyixi.common.NetworkConstant;
import stone.imitationyixi.utils.JsonUtils;
import stone.imitationyixi.utils.NetworkUtils;

/**
 * @author stone
 */

public class RecordModel implements IRecordModel {

    private final IRecordListener mListener;

    public RecordModel(IRecordListener listener) {
        mListener = listener;
    }

    @Override
    public Subscription getNetworkSubscription() {
        return NetworkUtils.doGet(NetworkConstant.getRecordListUrl("1"), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                Type type = new TypeToken<BaseListBean<RecordBean>>() {
                }.getType();
                BaseListBean<RecordBean> baseListBean = JsonUtils.parseJson(result, type);
                List<RecordBean> recordBeen = baseListBean.getData();
                mListener.onNetworkSuccess(recordBeen);
            }

            @Override
            public void onFail(Throwable e) {
                mListener.onNetworkError();
            }

            @Override
            public void onFinally() {
            }
        });
    }
}
