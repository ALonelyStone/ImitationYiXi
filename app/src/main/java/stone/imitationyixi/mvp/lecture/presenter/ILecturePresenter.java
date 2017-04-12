package stone.imitationyixi.mvp.lecture.presenter;

import rx.Subscription;

/**
 * @author Kotori
 * @time 2017/3/7  13:09
 * @desc ${TODD}
 */

public interface ILecturePresenter {
    /**
     * 进行网络请求
     */
    Subscription getNetworkSubscription(String id);

    /**
     * 进行小评论的网络请求
     */
    void netWorkForComments(String lecturerId);

    void netWorkForBottom(int lectureId);
}
