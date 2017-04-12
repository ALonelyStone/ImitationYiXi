package stone.imitationyixi.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import stone.imitationyixi.R;
import stone.imitationyixi.base.BaseHolder;
import stone.imitationyixi.bean.LecturerBean;
import stone.imitationyixi.utils.ImageUtils;
import stone.imitationyixi.utils.ViewUtils;

/**
 * @author stone
 */

public class LecturerHolder extends BaseHolder<LecturerBean> {

    private ImageView mIconIv;
    private TextView mNicknameTv;

    public LecturerHolder(String path) {
        super(path);
    }

    @Override
    protected View initHolderView() {
        View lecturerView = View.inflate(ViewUtils.getContext(), R.layout.gridview_item_lecturer, null);
        mIconIv = (ImageView) lecturerView.findViewById(R.id.lecturer_icon_iv);
        mNicknameTv = (TextView) lecturerView.findViewById(R.id.lecturer_nickname_tv);
        return lecturerView;
    }

    @Override
    protected void refreshHolderView(LecturerBean data) {
        ImageUtils.loadCircleImageAndCache(ViewUtils.getContext(), data.getPic(), mIconIv, 72 * 2);
        mNicknameTv.setText(data.getNickname());
    }
}
