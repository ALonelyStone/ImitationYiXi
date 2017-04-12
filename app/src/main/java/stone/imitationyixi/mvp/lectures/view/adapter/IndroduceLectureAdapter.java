package stone.imitationyixi.mvp.lectures.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import stone.imitationyixi.common.DatabaseConstant;
import stone.imitationyixi.mvp.lecture.view.activity.LectureActivity;
import stone.imitationyixi.utils.ImageUtils;

/**
 * @author 王维波
 * @time 2017/3/6  13:57
 * @desc ${TODD}
 */
public class IndroduceLectureAdapter extends PagerAdapter{
    //所以这里我们用普通的pageradapter就行了
    //页面数据较多,如果用fragmentpageradapter可能会照成因为大量fragment保存在内存中而赵成内存溢出

    private final Context mContext;
    private ArrayList<View> indroduceViews = new ArrayList<>();
    private ArrayList<String> ids = new ArrayList<>();

    public IndroduceLectureAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return indroduceViews!=null?indroduceViews.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(indroduceViews.get(position));
    }

    public void setData(List<Map<String, String>> imgs) {
        for (int i = 0; i < imgs.size(); i++) {
            ImageView iv = new ImageView(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(params);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            ids.add(imgs.get(i).get(DatabaseConstant.lecturesList._LECTUREID));
            ImageUtils.loadImage(mContext,imgs.get(i).get(DatabaseConstant.lecturesList._COVER),iv,200,100);  //是否有误
            indroduceViews.add(iv);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view =indroduceViews.get(position);
        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext,LectureActivity.class);
                intent.putExtra("lectureId",Integer.parseInt(ids.get(position)));
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
