package stone.imitationyixi.mvp.login.model;

import java.util.HashMap;

import rx.Subscription;
import stone.imitationyixi.ImitationYiXiApplication;
import stone.imitationyixi.bean.LonginAndRegistBean;
import stone.imitationyixi.bean.UserBean;
import stone.imitationyixi.common.NetworkConstant;
import stone.imitationyixi.common.SharedPreferenceConstant;
import stone.imitationyixi.mvp.login.presenter.ILoginPersenter;
import stone.imitationyixi.utils.JsonUtils;
import stone.imitationyixi.utils.NetworkUtils;
import stone.imitationyixi.utils.SharedPreferenceUtils;

/**
 * Created by Administrator on 2017/3/7.
 */

public class LoginModel implements IloginModel {

    private final ILoginPersenter mPersenter;

    public LoginModel(ILoginPersenter persenter) {
        mPersenter = persenter;
    }

    /**
     * 真正的登录入口,登录后进行帐号验证
     *
     * @param mail
     * @param pwd
     * @return
     */
    @Override
    public Subscription toLogin(String mail, String pwd) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("email", mail);
        hashMap.put("password", pwd);
        return NetworkUtils.doPost(NetworkConstant.getLoginUrl(), hashMap, new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                LonginAndRegistBean bean = JsonUtils.parseJson(result, LonginAndRegistBean.class);
                //验证帐号
                verifiedAccount(bean);
            }

            @Override
            public void onFail(Throwable e) {
                mPersenter.loginError(e.getMessage());
            }

            @Override
            public void onFinally() {

            }
        });
    }

    /**
     * 验证帐号
     *
     * @param bean
     */
    private void verifiedAccount(LonginAndRegistBean bean) {
        if (bean.getRes() == 0) {
            String uid = bean.getData();
            //保存UID到本地
            SharedPreferenceUtils.putString(ImitationYiXiApplication.getContext(), SharedPreferenceConstant.UID, uid);
            //请求用户信息
            requestUserInfo(uid);
        } else {
            //登录失败
            mPersenter.loginFail(bean);
        }
    }

    /**
     * 获取用户信息
     *
     * @param uid
     * @return
     */
    @Override
    public Subscription requestUserInfo(String uid) {
        return NetworkUtils.doGet(NetworkConstant.getUserInfoUrl(uid), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                UserBean resultBean = JsonUtils.parseJson(result, UserBean.class);
                ImitationYiXiApplication.setsUserInfo(resultBean.getData());
                if (mPersenter != null) {
                    mPersenter.loginSuccessful();
                }
            }

            @Override
            public void onFail(Throwable e) {
                if (mPersenter != null) {
                    mPersenter.loginError(e.getMessage());
                }
            }

            @Override
            public void onFinally() {

            }
        });
    }
}
