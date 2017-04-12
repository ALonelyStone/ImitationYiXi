package stone.imitationyixi.mvp.search.model;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.BaseBean;
import stone.imitationyixi.bean.LecturerBean;
import stone.imitationyixi.bean.SearchBean;
import stone.imitationyixi.common.NetworkConstant;
import stone.imitationyixi.utils.JsonUtils;
import stone.imitationyixi.utils.NetworkUtils;

/**
 * @author stone
 */

public class SearchModel implements ISearchModel {

    private final ISearchListener mListener;

    public SearchModel(ISearchListener listener) {
        mListener = listener;
    }

    @Override
    public Subscription getSearchNetworkSubscription(String searchText) {
        return NetworkUtils.doGet(NetworkConstant.getSearchUrl(searchText), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                Type type = new TypeToken<BaseBean<SearchBean>>() {
                }.getType();
                BaseBean<SearchBean> baseBean = JsonUtils.parseJson(result, type);
                SearchBean searchBean = baseBean.getData();
                List<SearchBean.LecsBean> lecsBeen = searchBean.getLecs();
                List<LecturerBean> lecturerBeen = searchBean.getLecturers();
                mListener.onNetWorkSuccess(lecsBeen, lecturerBeen);
            }

            @Override
            public void onFail(Throwable e) {
                e.printStackTrace();
                mListener.onNetworkError();
            }

            @Override
            public void onFinally() {
            }
        });
    }
}
