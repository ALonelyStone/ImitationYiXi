package stone.imitationyixi.mvp.lecturer.model;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.CategoryBean;
import stone.imitationyixi.bean.LecturerBean;

/**
 * @author stone
 */

public interface ILecturerModel {

    Subscription getCategoryNetwork();

    Subscription getLecturerNetwork(int id, int position);

    interface ILecturerListener {

        void onNetworkError();

        void onCategoryNetworkSuccess(List<CategoryBean> categoryBeen);

        void onLecturerNetworkSuccess(int position, List<LecturerBean> lecturerBeen);
    }

}
