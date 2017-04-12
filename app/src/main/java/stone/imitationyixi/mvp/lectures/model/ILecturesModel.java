package stone.imitationyixi.mvp.lectures.model;

import java.util.List;
import java.util.Map;

import rx.Subscription;

/**
 * @author 王维波
 * @time 2017/3/6  14:42
 * @desc ${TODD}
 */

public interface ILecturesModel {

    void getIndroduceLectures(boolean ifAscending, int i);
    void getBanner();

    void refreshListView();


    interface ILecturesModelListener{
        void onLecturesListDataFinished(List<Map<String, String>> data);

        void  onBannerCompleteListener(List<Map<String, String>> imgs);

        void onRefrshListViewComplete(Subscription data);
    }

}
