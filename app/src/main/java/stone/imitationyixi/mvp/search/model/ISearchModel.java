package stone.imitationyixi.mvp.search.model;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.LecturerBean;
import stone.imitationyixi.bean.SearchBean;

/**
 * @author stone
 */

public interface ISearchModel {

    /**
     * 返回搜索的网络请求
     */
    Subscription getSearchNetworkSubscription(String searchText);

    interface ISearchListener {

        /**
         * 网络请求错误的回调方法
         */
        void onNetworkError();

        /**
         * 网络请求成功的回调方法
         */
        void onNetWorkSuccess(List<SearchBean.LecsBean> lecsBean, List<LecturerBean> lecturerBeen);

    }

}
