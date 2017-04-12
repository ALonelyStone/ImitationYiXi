package stone.imitationyixi.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rx.Subscription;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;
import stone.imitationyixi.utils.TypefaceUtils;

/**
 * @author stone
 */

public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;
    protected Subscription mSubscription;

    /**
     * Fragment已经绑定到Activity时调用
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //传递绑定到的Activity
        mActivity = (Activity) context;
    }

    /**
     * Fragment开始创建时调用
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化网络请求
        mSubscription = initNetwork();
    }

    /**
     * Fragment开始创建视图时调用
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);
    }

    /**
     * 绑定到的Activity创建完成时调用
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListener();
        initData();
    }

    /**
     * Fragment销毁视图时调用
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //取消网络请求
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    /**
     * 初始化网络请求
     */
    protected Subscription initNetwork() {
        return null;
    }

    /**
     * 初始化视图
     */
    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    protected void initData() {
        TypefaceUtils.setTypefaceInActivity(mActivity, BaseActivity.getPath());
    }

    /**
     * 初始化监听
     */
    protected void initListener() {
    }
}
