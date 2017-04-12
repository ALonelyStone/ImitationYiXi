package stone.imitationyixi.mvp.branches.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.R;
import stone.imitationyixi.base.BaseFragment;
import stone.imitationyixi.bean.BranchesBean;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;
import stone.imitationyixi.mvp.branches.presenter.BranchesPresenter;
import stone.imitationyixi.mvp.branches.view.IBranchesView;
import stone.imitationyixi.utils.ImageUtils;
import stone.imitationyixi.utils.ToastUtils;
import stone.imitationyixi.utils.ViewUtils;

/**
 * Created by Administrator on 2017/3/6.
 */

public class BranchesFragment extends BaseFragment implements IBranchesView {

    private TextView mDescTv;
    private TextView mTitleTv;
    private ImageView mBranchesIv;
    private BranchesPresenter mBranchesPresenter;
    private List<BranchesBean> mBranchesBeen;

    @Override
    protected Subscription initNetwork() {
        mBranchesPresenter = new BranchesPresenter(this);
        return mBranchesPresenter.getNetworkSubscription();
    }

    @Override
    public void showNetworkError() {
        ToastUtils.showShortToast("网络连接失败，请检查一下网络", BaseActivity.getPath());
    }

    @Override
    public void networkFinish(List<BranchesBean> branchesBeen) {
        mBranchesBeen = branchesBeen;
        if (mBranchesIv != null && mTitleTv != null && mDescTv != null) {
            setViewData();
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_branches, container, false);
        mBranchesIv = (ImageView) inflate.findViewById(R.id.branches_iv);
        mTitleTv = (TextView) inflate.findViewById(R.id.branches_title_tv);
        mDescTv = (TextView) inflate.findViewById(R.id.branches_desc_tv);
        if (mBranchesBeen != null) {
            setViewData();
        }
        return inflate;
    }

    private void setViewData() {
        BranchesBean branchesBean = mBranchesBeen.get(0);
        ImageUtils.loadImageAndCache(ViewUtils.getContext(), branchesBean.getCover(), mBranchesIv, 748, 1000);
        mTitleTv.setText(branchesBean.getTitle());
        mDescTv.setText(branchesBean.getDesc());
    }
}
