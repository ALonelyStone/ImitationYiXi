package stone.imitationyixi.mvp.speech.view.activity;

import android.content.Intent;
import android.widget.TextView;

import stone.imitationyixi.R;
import stone.imitationyixi.mvp.base.view.activity.BaseActivity;

/**
 * @author Kotori
 * @time 2017/3/7  16:22
 * @desc ${TODD}
 */

public class SpeechActivity extends BaseActivity {

    private String mTitle;
    private String mName;
    private String mContent;
    private TextView mLectureFullContent;
    private TextView mLectureTitle;
    private TextView mLecturer;

    @Override
    protected int getContentView() {
        return R.layout.activity_speech;
    }

    @Override
    protected void initView() {
        super.initView();
        mLectureFullContent = (TextView)findViewById(R.id.lecture_full_content);
        mLectureTitle = (TextView) findViewById(R.id.lecture_title);
        mLecturer = (TextView)findViewById(R.id.lecturer);

    }

    @Override
    protected boolean isHasBack() {
        return true;
    }

    @Override
    public boolean isHasMore() {
        return false;
    }

    /**
     * 设置内容
     */
    private void setContent() {
        mLectureFullContent.setText(mContent);
        mLectureTitle.setText(mTitle);
        mLecturer.setText(mName);
    }

    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mName = intent.getStringExtra("name");
        mContent = intent.getStringExtra("content");
        setContent();
    }
}
