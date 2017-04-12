package stone.imitationyixi.mvp.lectures.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import stone.imitationyixi.R;
import stone.imitationyixi.common.DatabaseConstant;
import stone.imitationyixi.utils.ImageUtils;

/**
 * @author 王维波
 * @time 2017/3/6  14:05
 * @desc ${TODD}
 */
public class LectureListAdapter extends BaseAdapter {

    List<Map<String, String>> mData = new ArrayList<Map<String, String>>();
    Context mContext;
    private int mBeginIndex;
    private int mEndIndex;
    private Random mRandom;
    private List<Map<String, String>> mTotalData;

    public LectureListAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<Map<String, String>> data) {
        mTotalData = data;
        mData = getRandomData(data);
    }

    private List<Map<String, String>> getRandomData(List<Map<String, String>> data) {
        mRandom =  new Random();
        if(data==null ) {
            return null;
        }
        if(data.size()<10) {
            return data;
        }else if(data.size()<20){
            mBeginIndex = mRandom.nextInt(5)+5;
            mEndIndex = mRandom.nextInt(data.size() - mBeginIndex) + mBeginIndex;
        }else {
            mBeginIndex = mRandom.nextInt(10)+10;
            mEndIndex = mRandom.nextInt(data.size() - mBeginIndex) + mBeginIndex;
        }
        ArrayList<Map<String, String>> subData = new ArrayList<>();
        return getSubList(subData,data);
    }

    private List<Map<String, String>> getSubList(ArrayList<Map<String, String>> subData, List<Map<String, String>> data) {
        for (int i = 0; i < mEndIndex - mBeginIndex; i++) {
            subData.add(data.get(mBeginIndex));
            mBeginIndex++;
        }
        return subData;
    }

    @Override
    public int getCount() {
        return mData!=null?mData.size():0;
    }

    @Override
    public Object getItem(int position) {
        if(position ==mData.size()+1) {
            return null;
        }
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return -1;
    }

    //获取原数据后面的数据
    public void loadMore() {
        int loadedNum = mRandom.nextInt(mTotalData.size() - mEndIndex);
        for (int i = 0; i < loadedNum; i++) {
            mData.add(mTotalData.get(mEndIndex++));
        }
        notifyDataSetChanged();
    }

    class ViewHolder{
        ImageView pic;
        TextView tv_title;
        TextView tv_speaker;
        TextView tv_time;
        TextView tv_playtimes;
        TextView tv_liektime;
        TextView tv_reviewtime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder= null;
        if (convertView==null) {
            convertView= View.inflate(mContext,R.layout.lecture_items,null);
            holder=new ViewHolder();
            holder.pic=(ImageView) convertView.findViewById(R.id.img);
            holder.tv_title=(TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_speaker=(TextView) convertView.findViewById(R.id.tv_speaker);
            holder.tv_time=(TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_playtimes=(TextView) convertView.findViewById(R.id.tv_playtimes);
            holder.tv_liektime=(TextView) convertView.findViewById(R.id.tv_liektime);
            holder.tv_reviewtime=(TextView) convertView.findViewById(R.id.tv_reviewtime);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }
        ImageUtils.loadImage(mContext,mData.get(position).get(DatabaseConstant.lecturesList._COVER),holder.pic,100,70);
        holder.tv_title.setText(mData.get(position).get(DatabaseConstant.lecturesList._TITLE));
        holder.tv_speaker.setText(mData.get(position).get(DatabaseConstant.lecturesList._NICKNAME));
        holder.tv_time.setText(mData.get(position).get(DatabaseConstant.lecturesList._TIME));
        holder.tv_playtimes.setText(mData.get(position).get(DatabaseConstant.lecturesList._VIEWNUM));
        holder.tv_liektime.setText(mData.get(position).get(DatabaseConstant.lecturesList._LIKENUM));
        holder.tv_reviewtime.setText(mData.get(position).get(DatabaseConstant.lecturesList._CMTNUM));
        return convertView;
    }
}
