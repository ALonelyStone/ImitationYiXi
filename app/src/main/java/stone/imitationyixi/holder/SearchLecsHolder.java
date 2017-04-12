package stone.imitationyixi.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import stone.imitationyixi.R;
import stone.imitationyixi.base.BaseHolder;
import stone.imitationyixi.bean.SearchBean;
import stone.imitationyixi.utils.ImageUtils;
import stone.imitationyixi.utils.TypefaceUtils;
import stone.imitationyixi.utils.ViewUtils;

/**
 * @author stone
 */

public class SearchLecsHolder extends BaseHolder<SearchBean.LecsBean> {

    private ImageView mCoverIv;
    private TextView mTimeTv;
    private TextView mNicknameTv;
    private TextView mTitleTv;

    public SearchLecsHolder(String path) {
        super(path);
    }

    @Override
    protected View initHolderView() {
        View searchLecsView = View.inflate(ViewUtils.getContext(), R.layout.listview_item_lecs, null);
        TypefaceUtils.setTypefaceInView(searchLecsView, mPath);
        mTitleTv = (TextView) searchLecsView.findViewById(R.id.lecs_title_tv);
        mNicknameTv = (TextView) searchLecsView.findViewById(R.id.lecs_nickname_tv);
        mTimeTv = (TextView) searchLecsView.findViewById(R.id.lecs_time_tv);
        mCoverIv = (ImageView) searchLecsView.findViewById(R.id.lecs_cover_iv);
        return searchLecsView;
    }

    @Override
    protected void refreshHolderView(SearchBean.LecsBean data) {
        mTitleTv.setText(data.getTitle());
        mNicknameTv.setText(data.getLecturer().getNickname());
        mTimeTv.setText(data.getTime());
        mCoverIv.setMinimumWidth(128 * 2);
        ImageUtils.loadImageAndCache(ViewUtils.getContext(), data.getCover(), mCoverIv, 128 * 2, 72 * 2);
    }
}
