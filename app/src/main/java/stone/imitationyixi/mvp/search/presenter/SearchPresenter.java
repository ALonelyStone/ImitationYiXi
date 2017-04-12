package stone.imitationyixi.mvp.search.presenter;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.LecturerBean;
import stone.imitationyixi.bean.SearchBean;
import stone.imitationyixi.mvp.search.model.ISearchModel;
import stone.imitationyixi.mvp.search.model.SearchModel;
import stone.imitationyixi.mvp.search.view.ISearchView;

/**
 * @author stone
 */

public class SearchPresenter implements ISearchPresenter, ISearchModel.ISearchListener {

    private final ISearchModel mSearchModel;
    private final ISearchView mSearchView;

    public SearchPresenter(ISearchView searchView) {
        mSearchModel = new SearchModel(this);
        mSearchView = searchView;
    }

    @Override
    public Subscription getSearchNetworkSubscription(String searchText) {
        mSearchView.showLoading();
        return mSearchModel.getSearchNetworkSubscription(searchText);
    }

    @Override
    public void onNetworkError() {
        mSearchView.hideLoading();
        mSearchView.showNetworkError();
    }

    @Override
    public void onNetWorkSuccess(List<SearchBean.LecsBean> lecsBean, List<LecturerBean> lecturerBeen) {
        if (lecsBean.size()==0&&lecturerBeen.size()==0){
            mSearchView.showNoFound();
        }
        mSearchView.hideLoading();
        mSearchView.createLecsListView(lecsBean);
        mSearchView.createLecturesListView(lecturerBeen);
    }

}
