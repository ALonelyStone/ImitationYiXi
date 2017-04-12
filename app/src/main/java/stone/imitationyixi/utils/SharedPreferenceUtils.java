package stone.imitationyixi.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * @author stone
 *         应用配置数据保存工具类
 */

public class SharedPreferenceUtils {

    private static final String SHARED_PREFERENCE_FILE_NAME = "sharedPreferenceFileName";

    //==================== String ====================//

    /**
     * 保存一个String数据
     *
     * @param context 上下文
     * @param key     所保存的String数据对应的唯一Key值
     * @param value   想要保存的String数据
     * @return 是否保存成功
     */
    public static boolean putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 获取一个String数据
     *
     * @param context 上下文
     * @param key     所获取的String数据对应的唯一Key值
     * @return 想要获取的String数据（失败时返回空字符串）
     */
    public static String getString(Context context, String key) {
        return getString(context, key, "");
    }

    /**
     * 获取一个String数据
     *
     * @param context 上下文
     * @param key     所获取的String数据对应的唯一Key值
     * @param deValue 获取失败时返回的数据
     * @return 想要获取的String数据（失败时返回deValue）
     */
    public static String getString(Context context, String key, String deValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, deValue);
    }

    //==================== long ====================//

    /**
     * 保存一个long数据
     *
     * @param context 上下文
     * @param key     所保存的long数据对应的唯一Key值
     * @param value   想要保存的long数据
     * @return 是否保存成功
     */
    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().putLong(key, value).commit();
    }

    /**
     * 获取一个long数据
     *
     * @param context 上下文
     * @param key     所获取的long数据对应的唯一Key值
     * @return 想要获取的long数据（失败时返回0L）
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, 0L);
    }

    /**
     * 获取一个long数据
     *
     * @param context 上下文
     * @param key     所获取的long数据对应的唯一Key值
     * @param deValue 获取失败时返回的数据
     * @return 想要获取的long数据（失败时返回deValue）
     */
    public static long getLong(Context context, String key, long deValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, deValue);
    }

    //==================== int ====================//

    /**
     * 保存一个int数据
     *
     * @param context 上下文
     * @param key     所保存的int数据对应的唯一Key值
     * @param value   想要保存的int数据
     * @return 是否保存成功
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * 获取一个int数据
     *
     * @param context 上下文
     * @param key     所获取的int数据对应的唯一Key值
     * @return 想要获取的int数据（失败时返回0）
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, 0);
    }

    /**
     * 获取一个int数据
     *
     * @param context 上下文
     * @param key     所获取的int数据对应的唯一Key值
     * @param deValue 获取失败时返回的数据
     * @return 想要获取的int数据（失败时返回deValue）
     */
    public static int getInt(Context context, String key, int deValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, deValue);
    }

    //==================== boolean ====================//

    /**
     * 保存一个boolean数据
     *
     * @param context 上下文
     * @param key     所保存的boolean数据对应的唯一Key值
     * @param value   想要保存的boolean数据
     * @return 是否保存成功
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取一个String数据
     *
     * @param context 上下文
     * @param key     所获取的boolean数据对应的唯一Key值
     * @return 想要获取的boolean数据（失败时返回false）
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * 获取一个boolean数据
     *
     * @param context 上下文
     * @param key     所获取的boolean数据对应的唯一Key值
     * @param deValue 获取失败时返回的数据
     * @return 想要获取的boolean数据（失败时返回deValue）
     */
    public static boolean getBoolean(Context context, String key, boolean deValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, deValue);
    }

    //==================== float ====================//

    /**
     * 保存一个float数据
     *
     * @param context 上下文
     * @param key     所保存的float数据对应的唯一Key值
     * @param value   想要保存的float数据
     * @return 是否保存成功
     */
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.edit().putFloat(key, value).commit();
    }

    /**
     * 获取一个String数据
     *
     * @param context 上下文
     * @param key     所获取的float数据对应的唯一Key值
     * @return 想要获取的float数据（失败时返回0F）
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, 0F);
    }

    /**
     * 获取一个float数据
     *
     * @param context 上下文
     * @param key     所获取的float数据对应的唯一Key值
     * @param deValue 获取失败时返回的数据
     * @return 想要获取的float数据（失败时返回deValue）
     */
    public static float getFloat(Context context, String key, float deValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, deValue);
    }

}
