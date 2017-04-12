package stone.imitationyixi.mvp.lectures.presenter;

import android.content.Context;

import java.util.List;
import java.util.Map;

import rx.Subscription;
import stone.imitationyixi.mvp.lectures.model.ILecturesModel;
import stone.imitationyixi.mvp.lectures.model.LecturesModel;

/**
 * @author 王维波
 * @time 2017/3/6  14:15
 * @desc ${TODD}
 */

public class LecturesPresenter implements ILecturesPresenter,ILecturesModel.ILecturesModelListener {

    private ILecturesPresenterListener mListener;
    private LecturesModel mLecturesModel;

    public LecturesPresenter(Context context) {
        mLecturesModel = new LecturesModel(context,this);
    }

    @Override
    public void getIntroduceLectures(boolean ifAscending, int i) {
         mLecturesModel.getIndroduceLectures(ifAscending,i);
    }

    @Override
    public void getBanner() {
        mLecturesModel.getBanner();
    }

    @Override
    public void refreshListView() {
        mLecturesModel.refreshListView();
    }

    @Override
    public void onLecturesListDataFinished(List<Map<String, String>> data) {
        mListener.onLecturesListDataFinished(data);
    }

    @Override
    public void onBannerCompleteListener(List<Map<String, String>> imgs) {
        mListener.onBannerCompleteListener(imgs);
    }

    @Override
    public void onRefrshListViewComplete(Subscription data) {
        mListener.onRefrshListViewComplete(data);
    }

    public void setListener(ILecturesPresenterListener listener) {
        mListener = listener;
    }
}
