package stone.imitationyixi.mvp.lecturer.presenter;


import rx.Subscription;

/**
 * @author stone
 */

public interface ILecturerPresenter {

    Subscription getCategoryNetwork();

    Subscription getLecturerNetwork(int id, int position);

}
