package stone.imitationyixi.base;

import android.view.View;

/**
 * @author stone
 */

public abstract class BaseHolder<T> {

    protected final String mPath;
    public View mHolderView;
    public T mData;

    public BaseHolder(String path) {
        mPath = path;
        mHolderView = initHolderView();
        mHolderView.setTag(this);
    }

    /**
     * 设置数据
     */
    public void setData(T data) {
        mData = data;
        refreshHolderView(data);
    }

    /**
     * 初始化Holder视图
     */
    protected abstract View initHolderView();

    /**
     * 刷新Holder视图
     */
    protected abstract void refreshHolderView(T data);

}
