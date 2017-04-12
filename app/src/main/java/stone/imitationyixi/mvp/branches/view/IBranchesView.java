package stone.imitationyixi.mvp.branches.view;

import java.util.List;

import stone.imitationyixi.bean.BranchesBean;

/**
 * @author stone
 */

public interface IBranchesView {

    void showNetworkError();

    void networkFinish(List<BranchesBean> branchesBeen);

}
