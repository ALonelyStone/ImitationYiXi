package stone.imitationyixi.mvp.lecture.presenter;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.CommentsBean;
import stone.imitationyixi.bean.LectureBean;
import stone.imitationyixi.bean.RelatedBean;
import stone.imitationyixi.mvp.lecture.model.ILectureModel;
import stone.imitationyixi.mvp.lecture.model.LectureModel;
import stone.imitationyixi.mvp.lecture.view.ILectureView;

/**
 * @author Kotori
 * @time 2017/3/7  13:09
 * @desc ${TODD}
 */

public class LecturePresenter implements ILecturePresenter,ILectureModel.ILectureListener {

    private ILectureView mILectureView;
    private ILectureModel mILectureModel;

    public LecturePresenter(ILectureView lectureView) {
        mILectureModel = new LectureModel(this);
        mILectureView = lectureView;
    }

    @Override
    public Subscription getNetworkSubscription(String id) {
        return mILectureModel.networkAndDatabase(id);
    }

    @Override
    public void netWorkForComments(String lecturerId) {
        mILectureModel.networkToSmallComments(lecturerId);
    }

    @Override
    public void netWorkForBottom(int lectureId) {
        mILectureModel.networkToBottom(lectureId);
    }

    @Override
    public void onNetworkError() {
        mILectureView.showNetworkError();
    }

    @Override
    public void onNetworkFinish(LectureBean bean) {
        mILectureView.doNetworkFinish(bean);
    }

    @Override
    public void onNetworkSmallCommentsFinish(List<CommentsBean> bean) {
        mILectureView.doNetworkSmallCommentsFinish(bean);
    }

    @Override
    public void onNetworkBottomFinish(List<RelatedBean> relatedBeanList) {
        mILectureView.doNetworkBottomFinish(relatedBeanList);
    }
}
