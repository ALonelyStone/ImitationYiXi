package stone.imitationyixi.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import stone.imitationyixi.R;
import stone.imitationyixi.base.BaseHolder;
import stone.imitationyixi.bean.RelatedBean;
import stone.imitationyixi.utils.ImageUtils;
import stone.imitationyixi.utils.ViewUtils;

/**
 * @author Kotori
 * @time 2017/3/9  14:41
 * @desc ${TODD}
 */

public class RelatedHolder extends BaseHolder<RelatedBean> {

    private ImageView mImg; //缩略图
    private TextView mTvTitle;
    private TextView mTvSpeaker;
    private TextView mTvTime;
    private TextView mTvPlaytimes;
    private TextView mTvLiektime;
    private TextView mTvReviewtime;

    public RelatedHolder(String path) {
        super(path);
    }

    @Override
    protected View initHolderView() {
        View view = View.inflate(ViewUtils.getContext(), R.layout.user_coll_items, null);
        mImg = (ImageView) view.findViewById(R.id.img);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvSpeaker = (TextView) view.findViewById(R.id.tv_speaker);
        mTvTime = (TextView) view.findViewById(R.id.tv_time);
        mTvPlaytimes = (TextView) view.findViewById(R.id.tv_playtimes);
        mTvLiektime = (TextView) view.findViewById(R.id.tv_liektime);
        mTvReviewtime = (TextView) view.findViewById(R.id.tv_reviewtime);
        return view;
    }

    @Override
    protected void refreshHolderView(RelatedBean data) {
        ImageUtils.loadImageAndCache(ViewUtils.getContext(),data.getCover(),mImg,mImg.getWidth(),mImg.getHeight());
        mTvTitle.setText(data.getTitle());
        mTvSpeaker.setText(data.getLecturer().getNickname());
        mTvTime.setText(data.getTime());
        mTvPlaytimes.setText(data.getViewnum());
        mTvLiektime.setText(data.getLikenum());
        mTvReviewtime.setText(data.getCmtnum());
    }
}
