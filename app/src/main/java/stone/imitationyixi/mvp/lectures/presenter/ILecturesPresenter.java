package stone.imitationyixi.mvp.lectures.presenter;

import java.util.List;
import java.util.Map;

import rx.Subscription;

/**
 * @author 王维波
 * @time 2017/3/6  14:14
 * @desc ${TODD}
 */

public interface ILecturesPresenter {
    //直接从数据库获取演讲列表数据
    void getIntroduceLectures(boolean ifAscending, int i);
    //获取轮播图数据
    void getBanner();
    //先网络请求演讲列表数据保存到数据库,再从数据库中取出
    void refreshListView();


    interface ILecturesPresenterListener{
        void onLecturesListDataFinished(List<Map<String, String>> data);

        void onBannerCompleteListener(List<Map<String, String>> imgs);

        void onRefrshListViewComplete(Subscription data);
    }
}
