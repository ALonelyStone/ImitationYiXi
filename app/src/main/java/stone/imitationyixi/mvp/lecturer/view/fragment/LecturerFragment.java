package stone.imitationyixi.mvp.lecturer.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import stone.imitationyixi.R;
import stone.imitationyixi.base.BaseAdapter;
import stone.imitationyixi.base.BaseFragment;
import stone.imitationyixi.base.BaseHolder;
import stone.imitationyixi.bean.CategoryBean;
import stone.imitationyixi.bean.LecturerBean;
import stone.imitationyixi.holder.LecturerHolder;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;
import stone.imitationyixi.mvp.lecture.view.activity.LectureActivity;
import stone.imitationyixi.mvp.lecturer.presenter.ILecturerPresenter;
import stone.imitationyixi.mvp.lecturer.presenter.LecturerPresenter;
import stone.imitationyixi.mvp.lecturer.view.ILecturerView;
import stone.imitationyixi.utils.ToastUtils;
import stone.imitationyixi.utils.ViewUtils;
import stone.imitationyixi.widget.titanic.Titanic;
import stone.imitationyixi.widget.titanic.TitanicTextView;

/**
 * Created by Administrator on 2017/3/6.
 */

public class LecturerFragment extends BaseFragment implements ILecturerView {

    private ArrayList<View> mViewList;
    private ArrayList<String> mTitleList;
    private ArrayList<Integer> mCategoryIdList;
    private ILecturerPresenter mLecturerPresenter;
    private ViewPager mLecturerViewPager;
    private TabLayout mLecturerTabLayout;
    private boolean mIsCategoryFinish;
    private ArrayList<GridView> mGridViewList;
    private Titanic mTitanic;

    @Override
    protected Subscription initNetwork() {
        mLecturerPresenter = new LecturerPresenter(this);
        return mLecturerPresenter.getCategoryNetwork();
    }

    @Override
    public void showNetworkError() {
        ToastUtils.showShortToast("网络连接失败，请检查一下网络", BaseActivity.getPath());
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View lecturerView = inflater.inflate(R.layout.fragment_lecturer, container, false);
        mLecturerViewPager = (ViewPager) lecturerView.findViewById(R.id.lecturer_viewpager);
        mLecturerViewPager.setOffscreenPageLimit(0);
        mLecturerTabLayout = (TabLayout) lecturerView.findViewById(R.id.lecturer_tablayout);
        if (mIsCategoryFinish) {
            setPagerAdapter();
        }
        return lecturerView;
    }

    @Override
    public void categoryNetworkIsFinish(List<CategoryBean> categoryBeen) {
        mCategoryIdList = new ArrayList<>();
        mTitleList = new ArrayList<>();
        mViewList = new ArrayList<>();
        mGridViewList = new ArrayList<>();
        for (CategoryBean categoryBean : categoryBeen) {
            mCategoryIdList.add(categoryBean.getId());
            mTitleList.add(categoryBean.getName());
            View viewPagerView = View.inflate(mActivity, R.layout.viewpager_layout_lecturer, null);
            mGridViewList.add((GridView) viewPagerView.findViewById(R.id.lecturer_gridview));
            mViewList.add(viewPagerView);
        }
        mIsCategoryFinish = true;
        if (mLecturerViewPager != null && mLecturerTabLayout != null) {
            setPagerAdapter();
        }
    }

    private void setPagerAdapter() {
        mLecturerTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (String title : mTitleList) {
            mLecturerTabLayout.addTab(mLecturerTabLayout.newTab().setText(title));
        }
        mLecturerTabLayout.setTabTextColors(ViewUtils.getColor(android.R.color.white), ViewUtils.getColor(android.R.color.white));
        mLecturerTabLayout.setSelectedTabIndicatorColor(ViewUtils.getColor(android.R.color.white));
        mLecturerTabLayout.setupWithViewPager(mLecturerViewPager);

        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public int getCount() {
                return mViewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                mLecturerPresenter.getLecturerNetwork(mCategoryIdList.get(position), position);
                TitanicTextView titanicTextView = (TitanicTextView) mViewList.get(position).findViewById(R.id.lecturer_titanic);
                mTitanic.start(titanicTextView);
                container.addView(mViewList.get(position));
                return mViewList.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleList.get(position);
            }
        };
        mLecturerViewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void lecturerNetworkIsFinish(int position, List<LecturerBean> lecturerBeen) {
        GridView gridView = mGridViewList.get(position);
        LecturerAdapter lecturerAdapter = new LecturerAdapter(gridView, lecturerBeen, BaseActivity.getPath());
        gridView.setAdapter(lecturerAdapter);
        mTitanic.cancel();
    }

    @Override
    protected void initData() {
        super.initData();
        mTitanic = new Titanic();
    }

    class LecturerAdapter extends BaseAdapter<LecturerBean> {

        public LecturerAdapter(AbsListView absListView, List<LecturerBean> datas, String path) {
            super(absListView, datas, path);
        }

        @Override
        protected BaseHolder getHolder() {
            return new LecturerHolder(mPath);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            List<LecturerBean.LecturesWithCoverBean> lecturesWithCoverBeen = mDatas.get(position).getLectures_with_cover();
            if (lecturesWithCoverBeen.size() == 1) {
                Intent intent = new Intent(getActivity(), LectureActivity.class);
                intent.putExtra("lectureId", lecturesWithCoverBeen.get(0).getId());
                startActivity(intent);
            } else {
                for (LecturerBean.LecturesWithCoverBean lecturesWithCoverBean : lecturesWithCoverBeen) {
                    Log.e("stone", lecturesWithCoverBean.getId() + "");
                }
            }
        }
    }

}
