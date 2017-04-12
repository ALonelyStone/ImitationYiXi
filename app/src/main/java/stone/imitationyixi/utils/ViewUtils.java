package stone.imitationyixi.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Process;

import java.io.File;

import stone.imitationyixi.ImitationYiXiApplication;

/**
 * @author stone
 */

public class ViewUtils {

    /**
     * 得到上下文
     */
    public static Context getContext() {
        return ImitationYiXiApplication.getContext();
    }

    /**
     * 得到Resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到String.xml中的字符串
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 得到String.xml中的字符串数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到Color.xml中的颜色值
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 得到包名
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * 得到内部缓存目录
     */
    public static File getCacheDir() {
        return getContext().getCacheDir();
    }

    /**
     * 得到文件目录
     */
    public static File getFilesDir() {
        return getContext().getFilesDir();
    }

    /**
     * 得到外部缓存目录
     */
    public static File getExternalCacheDir() {
        return getContext().getExternalCacheDir();
    }

    /**
     * 得到资源管理器
     */
    public static AssetManager getAssets() {
        return getContext().getAssets();
    }

    /**
     * 得到主线程ID
     */
    public static long getMainThreadId() {
        return ImitationYiXiApplication.getMainThreadId();
    }

    /**
     * 得到主线程的Handler
     */
    public static Handler getHandler() {
        return ImitationYiXiApplication.getHandler();
    }

    public static void postTaskSafely(Runnable task) {
        //得到当前线程ID
        long curThreadId = Process.myTid();
        long mainThreadId = getMainThreadId();
        if (curThreadId == mainThreadId) {
            //若在主线程中，就直接执行方法
            task.run();
        } else {
            //若在子线程中，则post到主线程Handler中执行
            getHandler().post(task);
        }
    }
}
