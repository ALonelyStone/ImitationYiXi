package stone.imitationyixi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stone.imitationyixi.bean.SeminarBean;
import stone.imitationyixi.common.DatabaseConstant;

/**
 * @author stone
 */

public class LectureListDao {

    private final SQLiteDatabase mDatabase;

    public LectureListDao(Context context) {
        ImitationYiXiOpenHelper helper = new ImitationYiXiOpenHelper(context);
        mDatabase = helper.getReadableDatabase();
    }

    /**
     * 清空数据表里的数据
     */
    public void clearAll() {
        mDatabase.delete(DatabaseConstant.lecturesList._TABLE, null, null);
    }

    /**
     * 保存演讲列表
     */
    public void saveLectureList(SeminarBean.LecturesBean bean) {
        ContentValues values;
        values = new ContentValues();
        values.put(DatabaseConstant.lecturesList._LECTUREID, bean.getId());
        values.put(DatabaseConstant.lecturesList._TITLE, bean.getTitle());
        values.put(DatabaseConstant.lecturesList._VIEWNUM, bean.getViewnum());
        values.put(DatabaseConstant.lecturesList._LIKENUM, bean.getLikenum());
        values.put(DatabaseConstant.lecturesList._CMTNUM, bean.getCmtnum());
        values.put(DatabaseConstant.lecturesList._COVER, bean.getCover());
        values.put(DatabaseConstant.lecturesList._TIME, bean.getTime());
        values.put(DatabaseConstant.lecturesList._NICKNAME, bean.getLecturer().getNickname());
        mDatabase.insert(DatabaseConstant.lecturesList._TABLE, null, values);

    }

    public enum OrderBy {
        viewNumAsc(DatabaseConstant.lecturesList._VIEWNUM),
        viewNumDesc(DatabaseConstant.lecturesList._VIEWNUM + " desc"),
        likeNumAsc(DatabaseConstant.lecturesList._LIKENUM),
        likeNumDesc(DatabaseConstant.lecturesList._LIKENUM + " desc"),
        timeAsc(DatabaseConstant.lecturesList._TIME),
        timeDesc(DatabaseConstant.lecturesList._TIME + " desc");

        private String mOrderBy;

        OrderBy(String orderBy) {
            mOrderBy = orderBy;
        }

        public String getOrderBy() {
            return mOrderBy;
        }
    }

    /**
     * 读取演讲列表
     *
     * @param orderBy 偏序方式：
     *                <p>DatabaseConstant.lecturesList._VIEWNUM 观看
     *                <p>DatabaseConstant.lecturesList._LIKENUM 喜欢
     *                <p>DatabaseConstant.lecturesList._TIME 时间
     * @return 装着列表需要展示的数据的List
     * <p>Map的Key在DatabaseConstant里
     */
    public List<Map<String, String>> loadLectureList(OrderBy orderBy) {
        Cursor cursor = mDatabase.query(DatabaseConstant.lecturesList._TABLE, new String[]{
                        DatabaseConstant.lecturesList._LECTUREID,
                        DatabaseConstant.lecturesList._TITLE,
                        DatabaseConstant.lecturesList._VIEWNUM,
                        DatabaseConstant.lecturesList._LIKENUM,
                        DatabaseConstant.lecturesList._CMTNUM,
                        DatabaseConstant.lecturesList._COVER,
                        DatabaseConstant.lecturesList._TIME,
                        DatabaseConstant.lecturesList._NICKNAME},
                null, null, null, null, orderBy.getOrderBy());
        if (cursor == null || cursor.getColumnCount() == 0) {
            return null;
        }
        List<Map<String, String>> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            map.put(DatabaseConstant.lecturesList._LECTUREID, cursor.getString(cursor.getColumnIndex(DatabaseConstant.lecturesList._LECTUREID)));
            map.put(DatabaseConstant.lecturesList._TITLE, cursor.getString(cursor.getColumnIndex(DatabaseConstant.lecturesList._TITLE)));
            map.put(DatabaseConstant.lecturesList._VIEWNUM, cursor.getString(cursor.getColumnIndex(DatabaseConstant.lecturesList._VIEWNUM)));
            map.put(DatabaseConstant.lecturesList._LIKENUM, cursor.getString(cursor.getColumnIndex(DatabaseConstant.lecturesList._LIKENUM)));
            map.put(DatabaseConstant.lecturesList._CMTNUM, cursor.getString(cursor.getColumnIndex(DatabaseConstant.lecturesList._CMTNUM)));
            map.put(DatabaseConstant.lecturesList._COVER, cursor.getString(cursor.getColumnIndex(DatabaseConstant.lecturesList._COVER)));
            map.put(DatabaseConstant.lecturesList._TIME, cursor.getString(cursor.getColumnIndex(DatabaseConstant.lecturesList._TIME)));
            map.put(DatabaseConstant.lecturesList._NICKNAME, cursor.getString(cursor.getColumnIndex(DatabaseConstant.lecturesList._NICKNAME)));
            list.add(map);
        }
        cursor.close();
        return list;
    }

}
