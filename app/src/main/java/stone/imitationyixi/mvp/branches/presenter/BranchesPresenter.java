package stone.imitationyixi.mvp.branches.presenter;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.BranchesBean;
import stone.imitationyixi.mvp.branches.model.BranchesModel;
import stone.imitationyixi.mvp.branches.model.IBranchesModel;
import stone.imitationyixi.mvp.branches.view.IBranchesView;

/**
 * @author stone
 */

public class BranchesPresenter implements IBranchesPresenter, IBranchesModel.IBranchesListener {

    private final IBranchesView mBranchesView;
    private final IBranchesModel mBranchesModel;

    public BranchesPresenter(IBranchesView branchesView) {
        mBranchesModel = new BranchesModel(this);
        mBranchesView = branchesView;
    }

    @Override
    public Subscription getNetworkSubscription() {
        return mBranchesModel.getNetworkSubscription();
    }

    @Override
    public void onNetworkError() {
        mBranchesView.showNetworkError();
    }

    @Override
    public void onNetworkSuccess(List<BranchesBean> branchesBeen) {
        mBranchesView.networkFinish(branchesBeen);
    }
}
