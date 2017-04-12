package stone.imitationyixi.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import stone.imitationyixi.R;
import stone.imitationyixi.base.BaseHolder;
import stone.imitationyixi.bean.LecturerBean;
import stone.imitationyixi.utils.ImageUtils;
import stone.imitationyixi.utils.TypefaceUtils;
import stone.imitationyixi.utils.ViewUtils;

/**
 * @author stone
 */

public class SearchLecturersHolder extends BaseHolder<LecturerBean> {

    private ImageView mCoverIv;
    private TextView mNicknameTv;
    private TextView mTitleTv;

    public SearchLecturersHolder(String path) {
        super(path);
    }

    @Override
    protected View initHolderView() {
        View searchLecsView = View.inflate(ViewUtils.getContext(), R.layout.listview_item_lecs, null);
        TypefaceUtils.setTypefaceInView(searchLecsView,mPath);
        mTitleTv = (TextView) searchLecsView.findViewById(R.id.lecs_title_tv);
        mNicknameTv = (TextView) searchLecsView.findViewById(R.id.lecs_nickname_tv);
        mCoverIv = (ImageView) searchLecsView.findViewById(R.id.lecs_cover_iv);
        return searchLecsView;
    }

    @Override
    protected void refreshHolderView(LecturerBean data) {
        mTitleTv.setText(data.getNickname());
        mNicknameTv.setText(data.getDesc());
        mCoverIv.setMinimumWidth(72 * 2);
        ImageUtils.loadCircleImageAndCache(ViewUtils.getContext(), data.getPic(), mCoverIv, 72 * 2);
    }
}
