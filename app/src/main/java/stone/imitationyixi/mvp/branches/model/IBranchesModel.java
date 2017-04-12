package stone.imitationyixi.mvp.branches.model;

import java.util.List;

import rx.Subscription;
import stone.imitationyixi.bean.BranchesBean;

/**
 * @author stone
 */

public interface IBranchesModel {

    Subscription getNetworkSubscription();

    interface IBranchesListener{

        void onNetworkError();

        void onNetworkSuccess(List<BranchesBean> branchesBeen);
    }

}
