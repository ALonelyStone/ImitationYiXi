package stone.imitationyixi.mvp.search.view;

import java.util.List;

import stone.imitationyixi.bean.LecturerBean;
import stone.imitationyixi.bean.SearchBean;

/**
 * @author stone
 */

public interface ISearchView {

    /**
     * 创建讲座列表
     */
    void createLecsListView(List<SearchBean.LecsBean> lecsBean);

    /**
     * 创建讲师列表
     */
    void createLecturesListView(List<LecturerBean> lecturerBeen);

    /**
     * 展示网络错误
     */
    void showNetworkError();

    void showLoading();

    void hideLoading();

    void showNoFound();

}
