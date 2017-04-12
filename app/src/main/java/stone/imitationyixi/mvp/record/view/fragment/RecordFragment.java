package stone.imitationyixi.mvp.record.view.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import stone.imitationyixi.R;
import stone.imitationyixi.base.BaseFragment;
import stone.imitationyixi.bean.RecordBean;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;
import stone.imitationyixi.mvp.record.presenter.RecordPresenter;
import stone.imitationyixi.mvp.record.view.IRecordView;
import stone.imitationyixi.utils.ImageUtils;
import stone.imitationyixi.utils.ToastUtils;
import stone.imitationyixi.utils.TypefaceUtils;
import stone.imitationyixi.utils.ViewUtils;
import stone.imitationyixi.widget.titanic.Titanic;
import stone.imitationyixi.widget.titanic.TitanicTextView;

/**
 * Created by Administrator on 2017/3/6.
 */

public class RecordFragment extends BaseFragment implements IRecordView {

    private ViewPager mRecordViewPager;
    private RecordPresenter mRecordPresenter;
    private List<RecordBean> mRecordBeen;
    private Titanic mTitanic;
    private TitanicTextView mTitanicTextView;

    @Override
    protected Subscription initNetwork() {
        mRecordPresenter = new RecordPresenter(this);
        return mRecordPresenter.getNetworkSubscription();
    }

    @Override
    public void hideLoading() {
        mTitanic.cancel();
        mTitanicTextView.setVisibility(View.GONE);
    }

    @Override
    public void showNetworkError() {
        ToastUtils.showShortToast("网络连接失败，请检查一下网络", BaseActivity.getPath());
    }

    @Override
    public void doNetworkSuccess(List<RecordBean> recordBeen) {
        mRecordBeen = recordBeen;
        if (mRecordViewPager != null) {
            setPagerAdapter();
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View recordView = inflater.inflate(R.layout.fragment_record, container, false);
        mRecordViewPager = (ViewPager) recordView.findViewById(R.id.record_viewpager);
        mRecordViewPager.setOffscreenPageLimit(10);
        mTitanicTextView = (TitanicTextView) recordView.findViewById(R.id.record_titanic);
        mTitanic = new Titanic();
        mTitanic.start(mTitanicTextView);
        if (mRecordBeen != null) {
            setPagerAdapter();
        }
        return recordView;
    }

    private void setPagerAdapter() {
        final ArrayList<View> viewList = new ArrayList<>();
        for (int i = 0; i < mRecordBeen.size(); i++) {
            View recordItemView = View.inflate(ViewUtils.getContext(), R.layout.fragment_record_item, null);
            viewList.add(recordItemView);
        }

        PagerAdapter PagerAdapter = new PagerAdapter() {

            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }


            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                RecordBean recordBean = mRecordBeen.get(position);
                View recordItemView = viewList.get(position);

                ImageView recordBackground = (ImageView) recordItemView.findViewById(R.id.record_background_iv);
                ImageView recordCover = (ImageView) recordItemView.findViewById(R.id.lecture_cover);

                ImageUtils.loadImageAndCache(ViewUtils.getContext(), recordBean.getBackground().replace(".1536x1536", ""), recordBackground,
                        recordBackground.getWidth(), recordBackground.getHeight());
                ImageUtils.loadImageAndCache(ViewUtils.getContext(), recordBean.getCover(), recordCover,
                        recordCover.getWidth(), recordCover.getHeight());
                ((TextView) recordItemView.findViewById(R.id.record_title_tv)).setText(recordBean.getTitle());
                ((TextView) recordItemView.findViewById(R.id.record_desc_tv)).setText(recordBean.getDesc());
                ((TextView) recordItemView.findViewById(R.id.lecture_played_number)).setText("播放" + recordBean.getViewnum() + "次");
                ((TextView) recordItemView.findViewById(R.id.lecture_liked_number)).setText("" + recordBean.getLikenum() + "人点赞");

                TypefaceUtils.setTypefaceInView(recordItemView, BaseActivity.getPath());
                container.addView(recordItemView);
                return recordItemView;
            }
        };
        mRecordViewPager.setAdapter(PagerAdapter);
    }

}
