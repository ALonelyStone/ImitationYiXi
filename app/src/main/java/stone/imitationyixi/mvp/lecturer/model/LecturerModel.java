package stone.imitationyixi.mvp.lecturer.model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.BaseListBean;
import stone.imitationyixi.bean.CategoryBean;
import stone.imitationyixi.bean.LecturerBean;
import stone.imitationyixi.common.NetworkConstant;
import stone.imitationyixi.utils.JsonUtils;
import stone.imitationyixi.utils.NetworkUtils;

/**
 * @author stone
 */

public class LecturerModel implements ILecturerModel {

    private final ILecturerListener mListener;

    public LecturerModel(ILecturerListener listener) {
        mListener = listener;
    }

    @Override
    public Subscription getCategoryNetwork() {
        return NetworkUtils.doGet(NetworkConstant.getCategoryListUrl(), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                Type type = new TypeToken<BaseListBean<CategoryBean>>() {
                }.getType();
                BaseListBean<CategoryBean> baseListBean = JsonUtils.parseJson(result, type);
                List<CategoryBean> categoryBeen = baseListBean.getData();
                mListener.onCategoryNetworkSuccess(categoryBeen);
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

    @Override
    public Subscription getLecturerNetwork(int id, final int position) {
        return NetworkUtils.doGet(NetworkConstant.getLecturersByCategoryUrl(String.valueOf(id)), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                Type type = new TypeToken<BaseListBean<LecturerBean>>() {
                }.getType();
                BaseListBean<LecturerBean> beanBaseListBean = JsonUtils.parseJson(result, type);
                List<LecturerBean> lecturerBeen = beanBaseListBean.getData();
                mListener.onLecturerNetworkSuccess(position, lecturerBeen);
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
