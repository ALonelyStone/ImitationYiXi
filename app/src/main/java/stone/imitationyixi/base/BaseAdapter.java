package stone.imitationyixi.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stone
 */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter implements AdapterView.OnItemClickListener {

    protected final String mPath;
    public AbsListView mAbsListView;
    protected List<T> mDatas = new ArrayList<>();
    protected int mPosition;

    public BaseAdapter(AbsListView absListView, List<T> datas ,String path) {
        mDatas = datas;
        mPath = path;
        mAbsListView = absListView;
        mAbsListView.setOnItemClickListener(this);
    }

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mDatas != null ? mDatas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mPosition = position;
        BaseHolder baseHolder;
        if (convertView == null) {
            baseHolder = getHolder();
        } else {
            baseHolder = (BaseHolder) convertView.getTag();
        }
        baseHolder.setData(mDatas.get(position));
        return baseHolder.mHolderView;
    }

    /**
     * 设定ListView里的Item布局Holder
     */
    protected abstract BaseHolder getHolder();

    @Override
    public abstract void onItemClick(AdapterView<?> parent, View view, int position, long id);
}
