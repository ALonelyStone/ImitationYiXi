package stone.imitationyixi.mvp.comments.view.adapter;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import java.util.List;

import stone.imitationyixi.base.BaseAdapter;
import stone.imitationyixi.base.BaseHolder;
import stone.imitationyixi.bean.CommentsBean;
import stone.imitationyixi.holder.CommentsHolder;

/**
 * @author Kotori
 * @time 2017/3/7  16:59
 * @desc ${TODD}
 */

public class CommentAdapter extends BaseAdapter<CommentsBean> {

    private boolean mIsSmallComments = true;
    private List<CommentsBean> mDatas;

    public CommentAdapter(AbsListView absListView, List<CommentsBean> datas, String path) {
        super(absListView, datas, path);
        mDatas = datas;
    }

    @Override
    public int getCount() {
        if(mIsSmallComments) {
            return mDatas.size() >= 5 ? 5 : mDatas.size();
        }
        return super.getCount();
    }

    /**
     * 区分小评论与全部评论
     */
    public void isSmallComments(boolean isSmallComments){
        mIsSmallComments = isSmallComments;
    }

    @Override
    protected BaseHolder getHolder() {
        return new CommentsHolder(mPath);
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {

    }
}
