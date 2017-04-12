package stone.imitationyixi.mvp.login.model;

import java.util.HashMap;

import rx.Subscription;
import stone.imitationyixi.bean.LonginAndRegistBean;
import stone.imitationyixi.common.NetworkConstant;
import stone.imitationyixi.mvp.login.presenter.IRegistPersenter;
import stone.imitationyixi.utils.JsonUtils;
import stone.imitationyixi.utils.NetworkUtils;

/**
 * Created by Administrator on 2017/3/7.
 */
public class RegistModel implements IRegistModel {

    private final IRegistPersenter mPersenter;

    public RegistModel(IRegistPersenter persenter) {
        mPersenter = persenter;
    }

    /**
     * 注册帐号的实际操作
     *
     * @param mail
     * @param pwd
     * @return
     */
    @Override
    public Subscription registUser(String mail, String pwd) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("email", mail);
        hashMap.put("password", pwd);
        return NetworkUtils.doPost(NetworkConstant.getRegistUrl(), hashMap, new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                LonginAndRegistBean bean = JsonUtils.parseJson(result, LonginAndRegistBean.class);
                mPersenter.registResult(bean);//注册正常返回信息
            }

            @Override
            public void onFail(Throwable e) {
                mPersenter.registError(e.getMessage());//网络异常
            }

            @Override
            public void onFinally() {
            }
        });
    }
}
