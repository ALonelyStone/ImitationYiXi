package stone.imitationyixi.utils;

import android.widget.Toast;

/**
 * @author stone
 */

public class ToastUtils {

    /**
     * 弹出提示框（存在时间长），根据传入的字体路径（path），显示的字体不同
     */
    public static void showLongToast(CharSequence text, String path) {
        Toast.makeText(ViewUtils.getContext(), TypefaceUtils.setTypefaceInCharSequence(text, path), Toast.LENGTH_LONG).show();
    }

    /**
     * 弹出提示框（存在时间短），根据传入的字体路径（path），显示的字体不同
     */
    public static void showShortToast(CharSequence text, String path) {
        Toast.makeText(ViewUtils.getContext(), TypefaceUtils.setTypefaceInCharSequence(text, path), Toast.LENGTH_SHORT).show();
    }

}
