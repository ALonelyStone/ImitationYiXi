package stone.imitationyixi.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * @author stone
 *         意图工具类
 */

public class IntentUtils {

    /**
     * 进行activity跳转
     *
     * @param activity 目前的activity
     * @param clazz    要跳转到的下个activity的class
     */
    public static void startActivity(Activity activity, Class clazz) {
        startActivity(activity, clazz, false);
    }

    /**
     * 进行activity跳转，并设置是否关闭现在的activity
     *
     * @param activity 目前的activity
     * @param clazz    要跳转到的下个activity的class
     */
    public static void startActivity(Activity activity, Class clazz, boolean isFinish) {
        startActivity(activity, clazz, null, isFinish);
    }

    /**
     * 进行activity跳转，并设置获得返回值的code
     *
     * @param activity    目前的activity
     * @param clazz       要跳转到的下个activity的class
     * @param requestCode 跳转回来时，获得返回值的code
     */
    public static void startActivity(Activity activity, Class clazz, int requestCode) {
        startActivity(activity, clazz, requestCode, false);
    }

    /**
     * 进行activity跳转
     *
     * @param activity    目前的activity
     * @param clazz       要跳转到的下个activity的class
     * @param requestCode 跳转后的活动需要返回数据就传入int，否则传入null
     * @param isFinish    是否要销毁目前的activity
     */
    private static void startActivity(Activity activity, Class clazz, Integer requestCode, boolean isFinish) {
        Intent intent = new Intent(activity, clazz);
        if (requestCode == null) {
            activity.startActivity(intent);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
        if (isFinish) {
            activity.finish();
        }
    }

    /**
     * 开启service
     *
     * @param activity 目前的activity
     * @param clazz    要开启的service的class
     */
    public static void startService(Context activity, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startService(intent);
    }

    /**
     * 关闭service
     *
     * @param activity 目前的activity
     * @param clazz    要关闭的service的class
     */
    public static void stopService(Context activity, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.stopService(intent);
    }

}
