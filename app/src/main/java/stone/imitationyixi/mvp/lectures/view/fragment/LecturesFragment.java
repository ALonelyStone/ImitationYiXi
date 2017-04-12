package stone.imitationyixi.mvp.lectures.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import rx.Subscription;
import stone.imitationyixi.R;
import stone.imitationyixi.base.BaseFragment;
import stone.imitationyixi.common.DatabaseConstant;
import stone.imitationyixi.mvp.lecture.view.activity.LectureActivity;
import stone.imitationyixi.mvp.lectures.presenter.ILecturesPresenter;
import stone.imitationyixi.mvp.lectures.presenter.LecturesPresenter;
import stone.imitationyixi.mvp.lectures.view.XListView;
import stone.imitationyixi.mvp.lectures.view.adapter.IndroduceLectureAdapter;
import stone.imitationyixi.mvp.lectures.view.adapter.LectureListAdapter;
import stone.imitationyixi.mvp.lectures.view.adapter.OnPageChangeListenerAdapter;

/**
 * @author 王维波
 * @time 2017/3/6  12:36
 * @desc ${TODD}
 */

public class LecturesFragment extends BaseFragment implements ILecturesFragment, XListView.IXListViewListener,
        ILecturesPresenter.ILecturesPresenterListener, View.OnClickListener {

    private ViewPager indroduce;
    private TextView btn_date;
    private TextView btn_look;
    private TextView btn_fav;
    private XListView lecture_list;
    private LecturesPresenter iLecturesPresenter;
    private LectureListAdapter mLectureListAdapter;
    private IndroduceLectureAdapter mIndroduceLectureAdapter;
    private int currentId;
    private LinearLayout mAdIndicatorLl;
    private Timer mTimer;
    boolean ifAscending = true;
    private Handler mHandler = new Handler();
    private List<Map<String, String>> mListData;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lectures, null);
    }

    private void initUI() {
        indroduce = (ViewPager) getActivity().findViewById(R.id.indroduce);
        btn_date = (TextView) getActivity().findViewById(R.id.btn_date);
        btn_date.setOnClickListener(this);
        btn_look = (TextView) getActivity().findViewById(R.id.btn_look);
        btn_look.setOnClickListener(this);
        btn_fav = (TextView) getActivity().findViewById(R.id.btn_fav);
        btn_fav.setOnClickListener(this);
        lecture_list = (XListView) getActivity().findViewById(R.id.lecture_list);
        mAdIndicatorLl = (LinearLayout) getActivity().findViewById(R.id.ad_indicator);
        //模拟第一个被点击
        btn_date.performClick();

        //初始化演讲列表
        mLectureListAdapter = new LectureListAdapter(getActivity());
        lecture_list.setAdapter(mLectureListAdapter);
        lecture_list.setXListViewListener(this);
        lecture_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击跳到演讲详情页面
                Intent intent = new Intent(LecturesFragment.this.getContext(), LectureActivity.class);
                Object item = mLectureListAdapter.getItem(position-1);
                if (item != null) {
                    Map<String, String> map = (Map<String, String>) item;
                    intent.putExtra("lectureId", Integer.parseInt(map.get(DatabaseConstant.lecturesList._LECTUREID)));
                    startActivity(intent);
                } else {
                    int count = lecture_list.getCount();
                    onLoadMore();
                    lecture_list.smoothScrollToPosition(count);
                }
            }
        });

        //初始化轮播图
        // FragmentManager manager = ((MainActivity) getActivity()).getSupportFragmentManager();
        mIndroduceLectureAdapter = new IndroduceLectureAdapter(getActivity());
        indroduce.setAdapter(mIndroduceLectureAdapter);
        indroduce.setOnPageChangeListener(new OnPageChangeListenerAdapter() {
            @Override
            public void onPageSelected(int index) {
                int childCount = mAdIndicatorLl.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    mAdIndicatorLl.getChildAt(i).setEnabled(i == index);
                }
            }
        });
    }

    @Override
    protected void initData() {
        initUI();
        iLecturesPresenter.getIntroduceLectures(ifAscending, 1);
        iLecturesPresenter.getBanner();
    }

    @Override
    protected void initListener() {
        iLecturesPresenter = new LecturesPresenter(getActivity());
        iLecturesPresenter.setListener(this);
    }

    @Override
    public void onLecturesListDataFinished(List<Map<String, String>> data) {
        mLectureListAdapter.setData(data);
        mLectureListAdapter.notifyDataSetChanged();
        mListData = data;

    }

    @Override
    public void onBannerCompleteListener(List<Map<String, String>> list) {
        /*
        * 模拟已经请求数据成功并且有5个图片网址加载
        * */
        ArrayList<String> imgs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            imgs.add(list.get(i).get(DatabaseConstant.lecturesList._COVER));
        }
        mIndroduceLectureAdapter.setData(list);
        mIndroduceLectureAdapter.notifyDataSetChanged();
        initAdIndicatorContainer(imgs);
        initAdItemChangeTimer(imgs);
    }

    /**
     * 表示网络请求后并且保存最新的数据到数据库已经完成的监听器
     *
     * @param data
     */
    @Override
    public void onRefrshListViewComplete(Subscription data) {
        iLecturesPresenter.getIntroduceLectures(ifAscending, currentId);
    }

    /**
     * 初始化广告的指示器容器
     */
    private void initAdIndicatorContainer(final List<String> datas) {
        for (int i = 0; i < datas.size(); i++) {
            ImageView iv = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(15, 0, 0, 0);
            iv.setLayoutParams(params);
            iv.setBackgroundResource(R.drawable.ad_indicator_bg);
            iv.setEnabled(i == 0);
            mAdIndicatorLl.addView(iv);
        }
    }

    /**
     * 创建一个定时器 每3秒轮播一次
     */
    private void initAdItemChangeTimer(final List<String> datas) {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        changeAdItem(datas);
                    }
                });

            }
        }, 3000, 3000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTimer.cancel();
    }

    /**
     * 修改广告item的展示
     */
    private void changeAdItem(List<String> datas) {
        int currentItem = indroduce.getCurrentItem();
        currentItem++;
        //currentItem是自增长后当前item的索引 如果该索引大于最后一个数据的索引
        if (currentItem > datas.size() - 1) {
            currentItem = 0;
        }
        indroduce.setCurrentItem(currentItem, true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_date:
                OrderClicked(1);
                currentId = 1;
                changeBtnBackground(btn_date, currentId);
                break;
            case R.id.btn_look:
                OrderClicked(2);
                currentId = 2;
                changeBtnBackground(btn_look, currentId);
                break;
            case R.id.btn_fav:
                OrderClicked(3);
                currentId = 3;
                changeBtnBackground(btn_fav, currentId);
                break;
        }

    }

    private void OrderClicked(int id) {
        if (currentId == id) {
            ifAscending = !ifAscending;
        }
        iLecturesPresenter.getIntroduceLectures(ifAscending, id);

    }

    private void changeBtnBackground(TextView v, int id) {
        btn_date.setTextSize(15f);
        btn_date.setTextColor(Color.LTGRAY);
        btn_look.setTextSize(15f);
        btn_look.setTextColor(Color.LTGRAY);
        btn_fav.setTextSize(15f);
        btn_fav.setTextColor(Color.LTGRAY);
        int buttonNums = 3;
        for (int i = 1; i <= buttonNums; i++) {
            if (i == id) {
                v.setTextSize(18f);
                v.setTextColor(Color.WHITE);
            }
        }
    }

    private void onLoad() {

        lecture_list.stopRefresh();
        lecture_list.stopLoadMore();
        lecture_list.setRefreshTime("刚刚");
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mListData.clear();
                RefreshItems();     //从网络加载数据而不是数据库
                onLoad();
            }
        }, 2000);
    }

    //这里上拉刷新都是通过网络请求获取新数据
    private void RefreshItems() {
        iLecturesPresenter.refreshListView();
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLectureListAdapter.loadMore();
                onLoad();
            }
        }, 2000);
    }
}
