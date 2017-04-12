package stone.imitationyixi.mvp.search.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import stone.imitationyixi.R;
import stone.imitationyixi.base.BaseAdapter;
import stone.imitationyixi.base.BaseHolder;
import stone.imitationyixi.bean.LecturerBean;
import stone.imitationyixi.bean.SearchBean;
import stone.imitationyixi.holder.SearchLecsHolder;
import stone.imitationyixi.holder.SearchLecturersHolder;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;
import stone.imitationyixi.mvp.lecture.view.activity.LectureActivity;
import stone.imitationyixi.mvp.search.presenter.ISearchPresenter;
import stone.imitationyixi.mvp.search.presenter.SearchPresenter;
import stone.imitationyixi.mvp.search.view.ISearchView;
import stone.imitationyixi.utils.ToastUtils;
import stone.imitationyixi.utils.TypefaceUtils;
import stone.imitationyixi.utils.ViewUtils;
import stone.imitationyixi.widget.titanic.Titanic;
import stone.imitationyixi.widget.titanic.TitanicTextView;

/**
 * @author stone
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener, ISearchView, AbsListView.OnScrollListener {

    private ListView mLectureLv;
    private ListView mLecturerLv;
    /*private View mHeaderView;
    private TextView mHeaderTv;*/
    private EditText mSearchEt;
    private ImageButton mSearchIb;
    private ISearchPresenter mSearchPresenter;
    private TitanicTextView mTitanicTextView;
    private Titanic mTitanic;
    private ImageView mNoFoundIv;

    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        super.initView();
        mLectureLv = (ListView) findViewById(R.id.search_lecture_lv);
        mLecturerLv = (ListView) findViewById(R.id.search_lecturer_lv);
        mSearchEt = (EditText) findViewById(R.id.search_et);
        mSearchIb = (ImageButton) findViewById(R.id.search_ib);
        mTitanicTextView = (TitanicTextView) findViewById(R.id.search_titanic);
        mNoFoundIv = (ImageView) findViewById(R.id.search_not_found_iv);

        /*mHeaderView = findViewById(R.id.search_header_view);
        mHeaderTv = (TextView) mHeaderView.findViewById(R.id.header_tv);*/
    }

    @Override
    protected void initData() {
        super.initData();
        mTitanic = new Titanic();
        mSearchPresenter = new SearchPresenter(this);

        View lecsHeaderView = View.inflate(ViewUtils.getContext(), R.layout.widget_header, null);
        ((TextView) lecsHeaderView.findViewById(R.id.header_tv)).setText("讲座");
        TypefaceUtils.setTypefaceInView(lecsHeaderView, mPath);
        mLectureLv.addHeaderView(lecsHeaderView);

        View lecturerHeaderView = View.inflate(ViewUtils.getContext(), R.layout.widget_header, null);
        ((TextView) lecturerHeaderView.findViewById(R.id.header_tv)).setText("讲师");
        TypefaceUtils.setTypefaceInView(lecturerHeaderView, mPath);
        mLecturerLv.addHeaderView(lecturerHeaderView);
    }

    @Override
    protected void initListener() {
        mSearchIb.setOnClickListener(this);
        mLectureLv.setOnScrollListener(this);
        mLecturerLv.setOnScrollListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_ib:
                String searchText = mSearchEt.getText().toString();
                if (searchText.length() > 0) {
                    if (mSubscription != null && !mSubscription.isUnsubscribed()) {
                        mSubscription.unsubscribe();
                    }
                    mSubscription = mSearchPresenter.getSearchNetworkSubscription(searchText);
                } else {
                    ToastUtils.showShortToast("请输入搜索内容", mPath);
                }
                break;
        }
    }

    @Override
    public void createLecsListView(List<SearchBean.LecsBean> lecsBean) {
        LectureAdapter lectureAdapter = new LectureAdapter(mLectureLv, lecsBean, mPath);
        mLectureLv.setAdapter(lectureAdapter);
    }

    @Override
    public void createLecturesListView(List<LecturerBean> lecturerBeen) {
        LecturersAdapter lecturersAdapter = new LecturersAdapter(mLecturerLv, lecturerBeen, mPath);
        mLecturerLv.setAdapter(lecturersAdapter);
    }

    @Override
    public void showNetworkError() {
        ToastUtils.showShortToast("网络连接失败，请检查一下网络", mPath);
    }

    @Override
    public void showLoading() {
        mLectureLv.setVisibility(View.GONE);
        mLecturerLv.setVisibility(View.GONE);
        mNoFoundIv.setVisibility(View.GONE);
        mTitanicTextView.setVisibility(View.VISIBLE);
        mTitanic.start(mTitanicTextView);
    }

    @Override
    public void hideLoading() {
        mLectureLv.setVisibility(View.VISIBLE);
        mLecturerLv.setVisibility(View.VISIBLE);
        mTitanicTextView.setVisibility(View.GONE);
        mTitanic.cancel();
    }

    @Override
    public void showNoFound() {
        mNoFoundIv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        switch (view.getId()) {
            case R.id.search_lecture_lv:
                if (totalItemCount == 1) {
                    mLectureLv.setVisibility(View.GONE);
                }
                break;
            case R.id.search_lecturer_lv:
                if (totalItemCount == 1) {
                    mLecturerLv.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public boolean isHasTitle() {
        return false;
    }

    @Override
    public boolean isHasSearch() {
        return true;
    }

    @Override
    protected boolean isHasBack() {
        return true;
    }

    @Override
    public boolean isHasMore() {
        return false;
    }

    class LectureAdapter extends BaseAdapter<SearchBean.LecsBean> {

        public LectureAdapter(AbsListView absListView, List<SearchBean.LecsBean> datas, String path) {
            super(absListView, datas, path);
        }

        @Override
        protected BaseHolder getHolder() {
            return new SearchLecsHolder(mPath);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                SearchBean.LecsBean lecsBean = mDatas.get(position - 1);
                Intent intent = new Intent(SearchActivity.this, LectureActivity.class);
                intent.putExtra("lectureId", lecsBean.getId());
                startActivity(intent);
            }
        }
    }

    class LecturersAdapter extends BaseAdapter<LecturerBean> {

        public LecturersAdapter(AbsListView absListView, List<LecturerBean> datas, String path) {
            super(absListView, datas, path);
        }

        @Override
        protected BaseHolder getHolder() {
            return new SearchLecturersHolder(mPath);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                LecturerBean lecturerBean = mDatas.get(position - 1);
                List<LecturerBean.LecturesWithCoverBean> LecturesWithCoverBean = lecturerBean.getLectures_with_cover();
                if (LecturesWithCoverBean.size() == 1) {
                    Intent intent = new Intent(SearchActivity.this, LectureActivity.class);
                    intent.putExtra("lectureId", LecturesWithCoverBean.get(0).getId());
                    startActivity(intent);
                } else {
                    for (LecturerBean.LecturesWithCoverBean lecturesWithCoverBean : LecturesWithCoverBean)
                        Log.e("stone", lecturesWithCoverBean.getId() + "");
                }
            }
        }
    }

}
