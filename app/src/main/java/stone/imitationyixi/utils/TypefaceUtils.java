package stone.imitationyixi.utils;

import android.app.Activity;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.view.View;

import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;

/**
 * @author stone
 */

public class TypefaceUtils {

    /**
     * 给该活动设置字体
     */
    public static void setTypefaceInActivity(Activity activity, String path) {
        setTypeface(activity, path);
    }

    /**
     * 给该视图设置字体
     */
    public static void setTypefaceInView(View view, String path) {
        setTypeface(view, path);
    }

    /**
     * 给该文本设置字体
     */
    public static SpannableString setTypefaceInCharSequence(CharSequence charSequence, String path) {
        return setTypeface(charSequence, path);
    }

    private static SpannableString setTypeface(Object obj, String path) {
        TypefaceCollection typefaceCollection = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(ViewUtils.getAssets(), path))
                .create();
        TypefaceHelper.init(typefaceCollection);
        if (obj instanceof Activity) {
            TypefaceHelper.typeface((Activity) obj);
            return null;
        } else if (obj instanceof View) {
            TypefaceHelper.typeface((View) obj);
            return null;
        } else if (obj instanceof CharSequence) {
            return TypefaceHelper.typeface((CharSequence) obj);
        }
        return null;
    }
}
