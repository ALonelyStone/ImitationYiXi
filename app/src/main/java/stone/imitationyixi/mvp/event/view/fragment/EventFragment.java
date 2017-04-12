package stone.imitationyixi.mvp.event.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import stone.imitationyixi.R;
import stone.imitationyixi.base.BaseFragment;
import stone.imitationyixi.common.NetworkConstant;

/**
 * Created by Administrator on 2017/3/6.
 */

public class EventFragment extends BaseFragment {

    private WebView mWebview;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, null);
        mWebview = (WebView) view.findViewById(R.id.webview);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new WebViewClient());
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        mWebview.loadUrl(NetworkConstant.getActivityUrl()+"/htmlpage");
    }
}
