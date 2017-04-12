package stone.imitationyixi.mvp.lecture.view;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import java.util.List;

import stone.imitationyixi.base.BaseAdapter;
import stone.imitationyixi.base.BaseHolder;
import stone.imitationyixi.bean.RelatedBean;
import stone.imitationyixi.holder.RelatedHolder;

/**
 * @author Kotori
 * @time 2017/3/9  14:39
 * @desc ${TODD}
 */

public class RelatedAdapter extends BaseAdapter<RelatedBean> {

    public RelatedAdapter(AbsListView absListView, List<RelatedBean> datas, String path) {
        super(absListView, datas, path);
    }

    @Override
    protected BaseHolder getHolder() {
        return new RelatedHolder(mPath);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
