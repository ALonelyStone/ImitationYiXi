package stone.imitationyixi.mvp.login.view.fragment;

/**
 * Created by Administrator on 2017/3/7.
 */

public interface IRegistView extends BaseLoginView {

    void registSuccessful(String data);

    void registError(String message);
}
