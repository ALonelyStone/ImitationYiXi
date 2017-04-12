package stone.imitationyixi.mvp.comments.view.listener;

import android.widget.AbsListView;

/**
 * @author Kotori
 * @time 2017/3/8  20:22
 * @desc ${TODD}
 */

public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {

    private int currentPage = 0;
    private boolean loading = true;
    private int previousTotalItemCount = 0;
    private int visibleThreshold = 5;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if ((this.loading) && (totalItemCount > this.previousTotalItemCount)){
            this.loading = false;
            this.previousTotalItemCount = totalItemCount;
            this.currentPage = (1 + this.currentPage);
        }
        if ((!this.loading) && (totalItemCount - visibleItemCount <= firstVisibleItem + this.visibleThreshold)){
            onLoadMore(1 + this.currentPage, totalItemCount);
            this.loading = true;
        }
    }

    public abstract void onLoadMore(int page, int totalItemCount);
}
