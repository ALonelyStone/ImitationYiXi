package stone.imitationyixi.mvp.login.view;

import android.text.TextUtils;

import stone.imitationyixi.ImitationYiXiApplication;
import stone.imitationyixi.bean.UserBean;
import stone.imitationyixi.common.NetworkConstant;
import stone.imitationyixi.utils.JsonUtils;
import stone.imitationyixi.utils.NetworkUtils;

/**
 * Created by Administrator on 2017/3/7.
 */

public class DoLogin {

    public DoLogin(ILogin login) {
        if (login != null) {
            String uid = login.toLogin();
            if (TextUtils.isEmpty(uid)) return;
            getUserInfo(uid);
        }
    }

    private void getUserInfo(String uid) {
        NetworkUtils.doGet(NetworkConstant.getUserInfoUrl(uid), new NetworkUtils.NetworkStringCallBack() {
            @Override
            public void onSuccessResponse(String result) {
                UserBean resultBean = JsonUtils.parseJson(result, UserBean.class);
                ImitationYiXiApplication.setsUserInfo(resultBean.getData());
            }

            @Override
            public void onFail(Throwable e) {

            }

            @Override
            public void onFinally() {

            }
        });
    }
}
