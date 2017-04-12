package stone.imitationyixi.mvp.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import stone.imitationyixi.ImitationYiXiApplication;
import stone.imitationyixi.bean.LectureBean;
import stone.imitationyixi.mvp.user.adapter.listener.UserItemClickListener;

/**
 * Created by Administrator on 2017/3/8.
 */

public abstract class BaseUserDataAdapter extends RecyclerView.Adapter<BaseUserDataAdapter.UserDataHolder> {

    protected UserItemClickListener mListener;
    public List<LectureBean> mData;


    public void upData(List data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public UserDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ImitationYiXiApplication.getContext());
        return new UserDataHolder(onCreateView(inflater));
    }

    @Override
    public void onBindViewHolder(UserDataHolder holder, int position) {
        if (position > 0) {
            getItemId(position);
            initData(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return getCount();
    }

    protected abstract int getCount();

    class UserDataHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public UserDataHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            findViewById(itemView);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getPosition());
        }
    }

    public void setOnItemClick(UserItemClickListener listener) {
        mListener = listener;
    }

    protected abstract View onCreateView(LayoutInflater parent);

    protected abstract void findViewById(View itemView);

    protected abstract void initData(UserDataHolder holder, int position);
}
