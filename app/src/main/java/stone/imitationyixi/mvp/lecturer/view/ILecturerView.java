package stone.imitationyixi.mvp.lecturer.view;

import java.util.List;

import stone.imitationyixi.bean.CategoryBean;
import stone.imitationyixi.bean.LecturerBean;

/**
 * @author stone
 */

public interface ILecturerView {

    void showNetworkError();

    void categoryNetworkIsFinish(List<CategoryBean> categoryBeen);

    void lecturerNetworkIsFinish(int position, List<LecturerBean> lecturerBeen);

}
