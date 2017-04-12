package stone.imitationyixi.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPoolAdapter;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import stone.imitationyixi.R;

/**
 * @author stone
 *         图片加载工具类
 */

public class ImageUtils {

    private enum Type {
        complete, circle
    }

    /**
     * 加载一个圆形的图片，但不对其进行缓存
     *
     * @param context   加载这个行为所处的Activity或Fragment
     * @param url       图片的网址
     * @param imageView 加载到哪个ImageView上
     * @param diameter  这个圆的直径
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView, int diameter) {
        loadImage(context, url, imageView, diameter, diameter, false, Type.circle);
    }

    /**
     * 加载一个圆形的图片，但不对其进行缓存，同时也不设置其加载的大小
     *
     * @param context   加载这个行为所处的Activity或Fragment
     * @param url       图片的网址
     * @param imageView 加载到哪个ImageView上
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        loadImage(context, url, imageView, 0, 0, false, Type.circle);
    }

    /**
     * 加载一个圆形的图片，并对其进行缓存
     *
     * @param context   加载这个行为所处的Activity或Fragment
     * @param url       图片的网址
     * @param imageView 加载到哪个ImageView上
     * @param diameter  这个圆的直径
     */
    public static void loadCircleImageAndCache(Context context, String url, ImageView imageView, int diameter) {
        loadImage(context, url, imageView, diameter, diameter, true, Type.circle);
    }

    /**
     * 加载一个圆形的图片，并对其进行缓存，但不设置其加载的大小
     *
     * @param context   加载这个行为所处的Activity或Fragment
     * @param url       图片的网址
     * @param imageView 加载到哪个ImageView上
     */
    public static void loadCircleImageAndCache(Context context, String url, ImageView imageView) {
        loadImage(context, url, imageView, 0, 0, true, Type.circle);
    }

    /**
     * 加载一个没有进行裁剪的图片，但不对其进行缓存
     *
     * @param context      加载这个行为所处的Activity或Fragment
     * @param url          图片的网址
     * @param imageView    加载到哪个ImageView上
     * @param widthPixels  图片的宽
     * @param heightPixels 图片的高
     */
    public static void loadImage(Context context, String url, ImageView imageView, int widthPixels, int heightPixels) {
        loadImage(context, url, imageView, widthPixels, heightPixels, false, Type.complete);
    }

    /**
     * 加载一个没有进行裁剪的图片，但不对其进行缓存，同时也不设置其加载的大小
     *
     * @param context   加载这个行为所处的Activity或Fragment
     * @param url       图片的网址
     * @param imageView 加载到哪个ImageView上
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        loadImage(context, url, imageView, 0, 0, false, Type.complete);
    }

    /**
     * 加载一个没有进行裁剪的图片，并对其进行缓存
     *
     * @param context      加载这个行为所处的Activity或Fragment
     * @param url          图片的网址
     * @param imageView    加载到哪个ImageView上
     * @param widthPixels  图片的宽
     * @param heightPixels 图片的高
     */
    public static void loadImageAndCache(Context context, String url, ImageView imageView, int widthPixels, int heightPixels) {
        loadImage(context, url, imageView, widthPixels, heightPixels, true, Type.complete);
    }

    /**
     * 加载一个没有进行裁剪的图片，并对其进行缓存，但不设置其加载的大小
     *
     * @param context   加载这个行为所处的Activity或Fragment
     * @param url       图片的网址
     * @param imageView 加载到哪个ImageView上
     */
    public static void loadImageAndCache(Context context, String url, ImageView imageView) {
        loadImage(context, url, imageView, 0, 0, true, Type.complete);
    }

    private static void loadImage(Context context, String url, ImageView imageView, int widthPixels, int heightPixels, boolean isCache, Type type) {
        DrawableRequestBuilder<String> builder = Glide.with(context)
                .load(url)
                .skipMemoryCache(!isCache)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .centerCrop();
        if (widthPixels != 0 || heightPixels != 0) {
            builder.override(widthPixels, heightPixels);
        }
        switch (type) {
            case complete:
                break;
            case circle:
                builder
                        .placeholder(R.mipmap.icn_loading)
                        .bitmapTransform(new CropCircleTransformation(new BitmapPoolAdapter()));
                break;
        }
        builder.into(imageView);
    }

}
