package stone.imitationyixi.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import stone.imitationyixi.R;
import stone.imitationyixi.base.BaseHolder;
import stone.imitationyixi.bean.CommentsBean;
import stone.imitationyixi.utils.ImageUtils;
import stone.imitationyixi.utils.ViewUtils;

/**
 * @author Kotori
 * @time 2017/3/9  1:02
 * @desc ${TODD}
 */

public class CommentsHolder extends BaseHolder<CommentsBean> {

    private View mView;
    private ImageView mUserPortrait;
    private TextView mMUserNickname;
    private TextView mMReply;
    private TextView mMreplyNickname;
    private TextView mCommentTime;
    private TextView mCommentContent;

    public CommentsHolder(String path) {
        super(path);
    }

    @Override
    protected View initHolderView() {
        mView = View.inflate(ViewUtils.getContext(), R.layout.comment_item, null);
        mUserPortrait = (ImageView)mView.findViewById(R.id.comment_user_portrait);
        mMUserNickname = (TextView) mView.findViewById(R.id.comment_user_nickname);
        mMReply = (TextView) mView.findViewById(R.id.comment_reply);
        mMreplyNickname = (TextView) mView.findViewById(R.id.comment_reply_nickname);
        mCommentTime = (TextView) mView.findViewById(R.id.comment_time);
        mCommentContent = (TextView) mView.findViewById(R.id.comment_content);
        return mView;
    }

    @Override
    protected void refreshHolderView(CommentsBean data) {
        String pic = data.getUser().getPic();
        if(TextUtils.isEmpty(pic)) {
            mUserPortrait.setImageResource(R.mipmap.base_head90);
        } else {
            ImageUtils.loadCircleImageAndCache(ViewUtils.getContext(),pic,mUserPortrait,mUserPortrait.getWidth());
        }
        mMUserNickname.setText(data.getUser().getNickname());
        int to_id = data.getTo_id();
        if(to_id != 0) {
            mMReply.setVisibility(View.VISIBLE);
            mMreplyNickname.setVisibility(View.VISIBLE);
            mMreplyNickname.setText(data.getTouser().getNickname());
        } else {
            mMReply.setVisibility(View.GONE);
            mMreplyNickname.setVisibility(View.GONE);
        }
        mCommentTime.setText(data.getCreated_at());
        mCommentContent.setText(data.getContent());
    }
}
