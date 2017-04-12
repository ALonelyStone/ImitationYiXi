package stone.imitationyixi.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;


/**
 * @author stone
 *         Json工具类
 */

public class JsonUtils {

    private static Gson gson;

    /**
     * 解析Json字符串成Bean
     */
    public static <T> T parseJson(String json, Class<T> clz) {
        gson = new Gson();
        return gson.fromJson(json, clz);
    }

    /**
     * 解析Json字符串成Bean
     * <p>
     * Type type = new TypeToken< Bean< Bean>>() {}.getType();<p>
     * Bean< Bean> bean = new JsonUtils.fromJson(result, type);
     */
    public static <T> T parseJson(String json, Type type) {
        gson = new Gson();
        return gson.fromJson(json, type);
    }

}
