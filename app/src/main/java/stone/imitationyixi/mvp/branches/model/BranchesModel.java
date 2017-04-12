package stone.imitationyixi.mvp.branches.model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.BaseListBean;
import stone.imitationyixi.bean.BranchesBean;
import stone.imitationyixi.common.NetworkConstant;
import stone.imitationyixi.utils.JsonUtils;
import stone.imitationyixi.utils.NetworkUtils;

/**
 * @author stone
 */

public class BranchesModel implements IBranchesModel {

    private final IBranchesListener mListener;

    public BranchesModel(IBranchesListener listener) {
        mListener = listener;
    }

    @Override
    public Subscription getNetworkSubscription() {
        return NetworkUtils.doGet(NetworkConstant.getZhiyaUrl("1"), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                Type type = new TypeToken<BaseListBean<BranchesBean>>() {
                }.getType();
                BaseListBean<BranchesBean> baseListBean = JsonUtils.parseJson(result, type);
                List<BranchesBean> branchesBeen = baseListBean.getData();
                mListener.onNetworkSuccess(branchesBeen);
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
