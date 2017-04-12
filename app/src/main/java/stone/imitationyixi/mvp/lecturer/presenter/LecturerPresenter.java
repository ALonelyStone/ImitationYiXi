package stone.imitationyixi.mvp.lecturer.presenter;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.CategoryBean;
import stone.imitationyixi.bean.LecturerBean;
import stone.imitationyixi.mvp.lecturer.model.ILecturerModel;
import stone.imitationyixi.mvp.lecturer.model.LecturerModel;
import stone.imitationyixi.mvp.lecturer.view.ILecturerView;

/**
 * @author stone
 */

public class LecturerPresenter implements ILecturerPresenter, ILecturerModel.ILecturerListener {

    private final ILecturerView mLecturerView;
    private final ILecturerModel mLecturerModel;

    public LecturerPresenter(ILecturerView lecturerView) {
        mLecturerView = lecturerView;
        mLecturerModel = new LecturerModel(this);
    }

    @Override
    public Subscription getCategoryNetwork() {
        return mLecturerModel.getCategoryNetwork();
    }

    @Override
    public Subscription getLecturerNetwork(int id,int position) {
        return mLecturerModel.getLecturerNetwork(id,position);
    }

    @Override
    public void onNetworkError() {
        mLecturerView.showNetworkError();
    }

    @Override
    public void onCategoryNetworkSuccess(List<CategoryBean> categoryBeen) {
        mLecturerView.categoryNetworkIsFinish(categoryBeen);
    }

    @Override
    public void onLecturerNetworkSuccess(int position, List<LecturerBean> lecturerBeen) {
        mLecturerView.lecturerNetworkIsFinish(position, lecturerBeen);
    }
}
