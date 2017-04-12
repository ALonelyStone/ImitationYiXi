package stone.imitationyixi.mvp.setting.view.activity;

import android.app.AlertDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import stone.imitationyixi.R;
import stone.imitationyixi.common.SharedPreferenceConstant;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;
import stone.imitationyixi.mvp.setting.presenter.ISettingPresenter;
import stone.imitationyixi.mvp.setting.presenter.SettingPresenter;
import stone.imitationyixi.mvp.setting.view.ISettingView;
import stone.imitationyixi.utils.SharedPreferenceUtils;
import stone.imitationyixi.utils.ToastUtils;
import stone.imitationyixi.utils.TypefaceUtils;
import stone.imitationyixi.utils.ViewUtils;
import stone.imitationyixi.widget.titanic.Titanic;
import stone.imitationyixi.widget.titanic.TitanicTextView;

/**
 * @author stone
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener, ISettingView, RadioGroup.OnCheckedChangeListener {

    private LinearLayout mCleanCacheLl;
    private LinearLayout mTypefaceLl;
    private TextView mAboutTv;
    private TextView mCacheTv;
    private TextView mTypefaceTv;
    private ISettingPresenter mSettingPresenter;
    private View mCleanDialogView;
    private AlertDialog mCleanAlertDialog;
    private TextView mYesTv;
    private TextView mNoTv;
    private AlertDialog mTypefaceDialog;
    private TitanicTextView mTitanicTextView;
    private TextView mVersionTv;
    private Titanic mTitanic;
    private View mTypefaceDialogView;
    private RadioGroup mRg;
    private String[] mTypefaceUrls;
    private String[] mTypefaceNames;

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        super.initView();
        mCleanCacheLl = (LinearLayout) findViewById(R.id.setting_clean_cache_ll);
        mCacheTv = (TextView) findViewById(R.id.setting_cache_tv);
        mTypefaceLl = (LinearLayout) findViewById(R.id.setting_typeface_ll);
        mTypefaceTv = (TextView) findViewById(R.id.setting_typeface_tv);
        mAboutTv = (TextView) findViewById(R.id.setting_about_tv);
        mTitanicTextView = (TitanicTextView) findViewById(R.id.setting_titanic);
        mVersionTv = (TextView) findViewById(R.id.setting_version_tv);

        mCleanDialogView = View.inflate(this, R.layout.widget_clean_dialog, null);
        mYesTv = (TextView) mCleanDialogView.findViewById(R.id.dialog_yes_tv);
        mNoTv = (TextView) mCleanDialogView.findViewById(R.id.dialog_no_tv);

        mTypefaceDialogView = View.inflate(this, R.layout.widget_typeface_dialog, null);
        mRg = (RadioGroup) mTypefaceDialogView.findViewById(R.id.dialog_rg);
    }

    @Override
    protected void initData() {
        super.initData();
        mTitanic = new Titanic();
        mSettingPresenter = new SettingPresenter(this);
        getCacheSize();

        PackageManager manager = getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            mVersionTv.setText(info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mTypefaceNames = ViewUtils.getStringArray(R.array.typeface_name);
        mTypefaceUrls = ViewUtils.getStringArray(R.array.typeface_url);

        String text = "";

        String typeface = SharedPreferenceUtils.getString(ViewUtils.getContext(), SharedPreferenceConstant.TYPEFACE);
        for (int i = 0; i < mTypefaceNames.length; i++) {
            if (typeface.equals(mTypefaceUrls[i])) {
                text = mTypefaceNames[i];
                mTypefaceTv.setText(mTypefaceNames[i]);
            }
        }

        for (String typefaceName : mTypefaceNames) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(typefaceName);
            mRg.addView(radioButton);
            if (typefaceName.equals(text)) {
                mRg.check(radioButton.getId());
            }
        }
    }

    @Override
    protected void initListener() {
        mCleanCacheLl.setOnClickListener(this);
        mTypefaceLl.setOnClickListener(this);
        mAboutTv.setOnClickListener(this);
        mYesTv.setOnClickListener(this);
        mNoTv.setOnClickListener(this);
        mRg.setOnCheckedChangeListener(this);
    }

    @Override
    public void getCacheSize() {
        mTitanicTextView.setText("读取中…");
        mSettingPresenter.getCacheSize();
    }

    @Override
    public void setCacheSize(String size) {
        mCacheTv.setText(size);
    }

    @Override
    public void showLoading() {
        mCacheTv.setVisibility(View.GONE);
        mTitanicTextView.setVisibility(View.VISIBLE);
        mTitanic.start(mTitanicTextView);
    }

    @Override
    public void hideLoading() {
        mCacheTv.setVisibility(View.VISIBLE);
        mTitanicTextView.setVisibility(View.GONE);
        mTitanic.cancel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_clean_cache_ll:
                if (mCleanAlertDialog == null) {
                    TypefaceUtils.setTypefaceInView(mCleanDialogView, mPath);
                    mCleanAlertDialog = new AlertDialog.Builder(this)
                            .setView(mCleanDialogView)
                            .show();
                } else {
                    TypefaceUtils.setTypefaceInView(mCleanDialogView, mPath);
                    mCleanAlertDialog.show();
                }
                break;
            case R.id.setting_typeface_ll:
                if (mTypefaceDialog == null) {
                    TypefaceUtils.setTypefaceInView(mTypefaceDialogView, mPath);
                    mTypefaceDialog = new AlertDialog.Builder(this)
                            .setView(mTypefaceDialogView)
                            .show();
                } else {
                    TypefaceUtils.setTypefaceInView(mTypefaceDialogView, mPath);
                    mTypefaceDialog.show();
                }
                break;
            case R.id.setting_about_tv:
                ToastUtils.showLongToast("本应用基于一席APP进行开发\n  开发人员：\n" +
                        "        陈奕磊\n        周英广\n        石益强\n        王阳", mPath);
                break;
            case R.id.dialog_yes_tv:
                mTitanicTextView.setText("清理中…");
                mSettingPresenter.cleanCache();
                mCleanAlertDialog.dismiss();
                break;
            case R.id.dialog_no_tv:
                mCleanAlertDialog.dismiss();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int radioButtonId = group.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) mTypefaceDialogView.findViewById(radioButtonId);
        CharSequence text = radioButton.getText();
        for (int i = 0; i < mTypefaceNames.length; i++) {
            if (text.equals(mTypefaceNames[i])) {
                SharedPreferenceUtils.putString(ViewUtils.getContext(), SharedPreferenceConstant.TYPEFACE, mTypefaceUrls[i]);
                setTypeface(mTypefaceUrls[i]);
                mTypefaceTv.setText(mTypefaceNames[i]);
                mTypefaceDialog.dismiss();
                return;
            }
        }
    }

    @Override
    protected boolean isHasBack() {
        return true;
    }

    @Override
    public boolean isHasMore() {
        return false;
    }

    @Override
    public CharSequence getToolbarTitle() {
        return "设置";
    }

}
