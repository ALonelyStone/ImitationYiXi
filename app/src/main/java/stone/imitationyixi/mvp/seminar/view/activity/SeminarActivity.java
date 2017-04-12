package stone.imitationyixi.mvp.seminar.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import stone.imitationyixi.R;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;

/**
 * @author 王维波
 * @time 2017/3/9  4:30
 * @desc ${TODD}
 */
public class SeminarActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSeminarView();
    }

    private void initSeminarView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_seminar;
    }
}
