package stone.imitationyixi;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import stone.imitationyixi.bean.UserBean;
import stone.imitationyixi.database.ImitationYiXiOpenHelper;

/**
 * @author stone
 */

public class ImitationYiXiApplication extends Application {
    private static Context mContext;
    private static Handler mHandler;
    private static long mMainThreadId;

    private static UserBean sUserInfo;

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static void setsUserInfo(UserBean sUserInfo) {
        ImitationYiXiApplication.sUserInfo = sUserInfo;
        ImitationYiXiApplication.sUserInfo.setLogin(true);
    }

    public static UserBean getUserInfo() {
        if (sUserInfo == null) {
            synchronized (ImitationYiXiApplication.class) {
                if (sUserInfo == null) {
                    sUserInfo = new UserBean();
                }
            }
        }
        return sUserInfo;
    }

    @Override
    public void onCreate() {
        //上下文
        mContext = getApplicationContext();
        //主线程
        mHandler = new Handler();
        //主线程的ID
        mMainThreadId = Process.myTid();
        /*
         *  tid:thread id
         *  pid:process id
         *  uid:user id
         */
        super.onCreate();
        initDatabase();
    }

    private void initDatabase() {
        new ImitationYiXiOpenHelper(this).getReadableDatabase();
    }


}
