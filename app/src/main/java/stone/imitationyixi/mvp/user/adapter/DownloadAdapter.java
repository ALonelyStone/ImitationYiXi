package stone.imitationyixi.mvp.user.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import stone.imitationyixi.ImitationYiXiApplication;
import stone.imitationyixi.R;
import stone.imitationyixi.bean.LectureBean;
import stone.imitationyixi.utils.ImageUtils;

/**
 * Created by Administrator on 2017/3/8.
 */

public class DownloadAdapter extends BaseUserDataAdapter {

    private ImageView img;
    private TextView tvTitle;
    private TextView tvSpeaker;
    private TextView tvTime;
    private TextView tvPlaytimes;
    private TextView tvLiektime;
    private TextView tvReviewtime;


    @Override
    protected int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    protected View onCreateView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.user_coll_items, null);
    }

    @Override
    protected void findViewById(View itemView) {
        img = (ImageView) itemView.findViewById(R.id.img);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvSpeaker = (TextView) itemView.findViewById(R.id.tv_speaker);
        tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        tvPlaytimes = (TextView) itemView.findViewById(R.id.tv_playtimes);
        tvLiektime = (TextView) itemView.findViewById(R.id.tv_liektime);
        tvReviewtime = (TextView) itemView.findViewById(R.id.tv_reviewtime);
    }

    @Override
    protected void initData(UserDataHolder holder, int position) {
        LectureBean bean = mData.get(position);
        if (bean == null) return;
        ImageUtils.loadImageAndCache(ImitationYiXiApplication.getContext(), bean.getCover(), img, 0, 0);
        tvTitle.setText(bean.getTitle());
        tvSpeaker.setText(bean.getLecturer().getNickname() + "");
        tvTime.setText(bean.getTime());
        tvPlaytimes.setText(bean.getViewnum());
        tvLiektime.setText(bean.getLikenum());
        tvReviewtime.setText(bean.getCmtnum());
    }


}
