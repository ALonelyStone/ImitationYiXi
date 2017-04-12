package stone.imitationyixi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import stone.imitationyixi.common.DatabaseConstant;

/**
 * Created by Administrator on 2017/3/8.
 */

public class UserDataDao {
    private final SQLiteDatabase mDatabase;


    public UserDataDao(Context context) {
        ImitationYiXiOpenHelper helper = new ImitationYiXiOpenHelper(context);
        mDatabase = helper.getReadableDatabase();
    }

    /**
     * 保存一个收藏演讲
     */
    public void saveCollSpeech(int id) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.UserDataList._USERDATAITEM_ID, id);
        values.put(DatabaseConstant.UserDataList._TYPE, DatabaseConstant.UserDataList._COLLECTION);
        mDatabase.insert(DatabaseConstant.UserDataList._TABLE, null, values);
    }

    /**
     * 保存一个回复的演讲
     */
    public void saveReplySpeech(int id) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.UserDataList._USERDATAITEM_ID, id);
        values.put(DatabaseConstant.UserDataList._TYPE, DatabaseConstant.UserDataList._REPLY);
        mDatabase.insert(DatabaseConstant.UserDataList._TABLE, null, values);
    }

    /**
     * 保存一个下载的演讲
     */
    public void saveDownSpeech(int id) {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.UserDataList._USERDATAITEM_ID, id);
        values.put(DatabaseConstant.UserDataList._TYPE, DatabaseConstant.UserDataList._DOWNLOAD);
        mDatabase.insert(DatabaseConstant.UserDataList._TABLE, null, values);
    }

    /**
     * 删除一个收藏演讲
     *
     * @param id
     */
    public void removeCollSpeech(int id) {
        String where = DatabaseConstant.UserDataList._USERDATAITEM_ID + " = ? AND " + DatabaseConstant.UserDataList._TYPE + " = ?";
        mDatabase.delete(DatabaseConstant.UserDataList._TABLE, where, new String[]{id + "", DatabaseConstant.UserDataList._COLLECTION + ""});
    }

    /**
     * 删除一个回复的演讲
     *
     * @param id
     */
    public void removeReplySpeech(int id) {
        String where = DatabaseConstant.UserDataList._USERDATAITEM_ID + " = ? AND " + DatabaseConstant.UserDataList._TYPE + " = ?";
        mDatabase.delete(DatabaseConstant.UserDataList._TABLE, where, new String[]{id + "", DatabaseConstant.UserDataList._REPLY + ""});
    }

    /**
     * 删除一个下载演讲
     *
     * @param id
     */
    public void removeDownSpeech(int id) {
        String where = DatabaseConstant.UserDataList._USERDATAITEM_ID + " = ? AND " + DatabaseConstant.UserDataList._TYPE + " = ?";
        mDatabase.delete(DatabaseConstant.UserDataList._TABLE, where, new String[]{id + "", DatabaseConstant.UserDataList._DOWNLOAD + ""});
    }


    /**
     * 查找全部收藏的id
     *
     * @return
     */
    public List findAllCollSpeech() {
        String select = DatabaseConstant.UserDataList._TYPE + " = ?";
        Cursor cursor = mDatabase.query(DatabaseConstant.UserDataList._TABLE, new String[]{DatabaseConstant.UserDataList._USERDATAITEM_ID}, select, new String[]{DatabaseConstant.UserDataList._COLLECTION + ""}, null, null, null);
        if (cursor == null) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            list.add(id);
        }
        cursor.close();
        return list;
    }

    /**
     * 查找全部回复的id
     *
     * @return
     */
    public List findAllReplySpeech() {
        String select = DatabaseConstant.UserDataList._TYPE + " = ?";
        Cursor cursor = mDatabase.query(DatabaseConstant.UserDataList._TABLE, new String[]{DatabaseConstant.UserDataList._USERDATAITEM_ID}, select, new String[]{DatabaseConstant.UserDataList._REPLY + ""}, null, null, null);
        if (cursor == null) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            list.add(id);
        }

        cursor.close();
        return list;
    }

    /**
     * 查找全部下载的id
     *
     * @return
     */
    public List findAllDownSpeech() {
        String select = DatabaseConstant.UserDataList._TYPE + " = ?";
        Cursor cursor = mDatabase.query(DatabaseConstant.UserDataList._TABLE, new String[]{DatabaseConstant.UserDataList._USERDATAITEM_ID}, select, new String[]{DatabaseConstant.UserDataList._DOWNLOAD + ""}, null, null, null, null);
        if (cursor == null) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            list.add(id);
        }

        cursor.close();
        return list;
    }
}
