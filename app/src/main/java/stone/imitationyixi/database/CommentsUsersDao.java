package stone.imitationyixi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stone.imitationyixi.bean.UserBean;
import stone.imitationyixi.common.DatabaseConstant;

/**
 * @author Kotori
 * @time 2017/3/9  11:26
 * @desc ${TODD}
 */

public class CommentsUsersDao {
    private final SQLiteDatabase mDatabase;

    public CommentsUsersDao(Context context) {
        ImitationYiXiOpenHelper helper = new ImitationYiXiOpenHelper(context);
        mDatabase = helper.getWritableDatabase();
    }

    /**
     * 清空数据表里的数据
     */
    public void clearAll() {
        mDatabase.delete(DatabaseConstant.CommentsUsersList._TABLE, null, null);
    }

    /**
     * 保存发送的评论信息
     */
    public void saveSendUserMsg(UserBean bean,String time,String content,String toName){
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.CommentsUsersList._USERPIC,bean.getPic());
        values.put(DatabaseConstant.CommentsUsersList._USERNICKNAME,bean.getNickname());
        values.put(DatabaseConstant.CommentsUsersList._TIME,time);
        values.put(DatabaseConstant.CommentsUsersList._CONTENT,content);
        values.put(DatabaseConstant.CommentsUsersList._TONAME,toName);
        mDatabase.insert(DatabaseConstant.CommentsUsersList._TABLE,null,values);
    }

    public List<Map<String,String>> loadUserMsg(){
        Cursor cursor = mDatabase.query(DatabaseConstant.CommentsUsersList._TABLE, null, null, null, null, null, DatabaseConstant.CommentsUsersList._TIME + " DESC");
        List<Map<String,String>> list = new ArrayList<>();
        while (cursor.moveToNext()){
            Map<String,String> map = new HashMap<>();
            map.put(DatabaseConstant.CommentsUsersList._USERPIC,cursor.getString(cursor.getColumnIndex(DatabaseConstant.CommentsUsersList._USERPIC)));
            map.put(DatabaseConstant.CommentsUsersList._USERNICKNAME,cursor.getString(cursor.getColumnIndex(DatabaseConstant.CommentsUsersList._USERNICKNAME)));
            map.put(DatabaseConstant.CommentsUsersList._TIME,cursor.getString(cursor.getColumnIndex(DatabaseConstant.CommentsUsersList._TIME)));
            map.put(DatabaseConstant.CommentsUsersList._CONTENT,cursor.getString(cursor.getColumnIndex(DatabaseConstant.CommentsUsersList._CONTENT)));
            map.put(DatabaseConstant.CommentsUsersList._TONAME,cursor.getString(cursor.getColumnIndex(DatabaseConstant.CommentsUsersList._TONAME)));
            list.add(map);
        }
        return list;
    }
}
