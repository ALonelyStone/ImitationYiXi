package stone.imitationyixi.mvp.login.view.fragment;

/**
 * Created by Administrator on 2017/3/7.
 */
public interface ILoginView extends BaseLoginView{

    void loginError(String message);

    void loginSuccessful();
}
