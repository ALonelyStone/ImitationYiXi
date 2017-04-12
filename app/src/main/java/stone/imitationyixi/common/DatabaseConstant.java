package stone.imitationyixi.common;

import android.provider.BaseColumns;

/**
 * @author stone
 */

public final class DatabaseConstant {

    public static final String DB_FILE_NAME = "imitationYiXi.db";
    public static final int DB_VERSION = 1;

    public static final class lecturesList implements BaseColumns {
        public static final String _TABLE = "lecturesList";

        public static final String _LECTUREID = "lectureid";
        public static final String _TITLE = "title";
        public static final String _VIEWNUM = "viewnum";
        public static final String _LIKENUM = "likenum";
        public static final String _CMTNUM = "cmtnum";
        public static final String _COVER = "cover";
        public static final String _TIME = "time";
        public static final String _NICKNAME = "nickname";

        public static final String CREATE_SQL = "create table " +
                _TABLE + " (" +
                _ID + " integer primary key autoincrement," +
                _LECTUREID + " integer," +
                _TITLE + " text," +
                _VIEWNUM + " integer," +
                _LIKENUM + " integer," +
                _CMTNUM + " text," +
                _COVER + " text," +
                _TIME + " text," +
                _NICKNAME + " text);";
    }


    public static final class UserDataList implements BaseColumns {//用户收藏和下载列表的的数据库,只存id
        public static final String _TABLE = "userdataList";

        public static final String _USERDATAITEM_ID = "_userdataitem_id";

        public static final String _TYPE = "_type";
        public static final int _DOWNLOAD = 0;
        public static final int _COLLECTION = 1;
        public static final int _REPLY = 2;


        public static final String CREATE_SQL = "create table " +
                _TABLE + " (" +
                _ID + " integer primary key autoincrement," +
                _USERDATAITEM_ID + " integer," +
                _TYPE + " integer);";
    }

    public static final class CommentsUsersList implements BaseColumns {
        public static final String _TABLE = "commentsuserslist";

        public static final String _USERPIC = "userpic";
        public static final String _USERNICKNAME = "usernickname";
        public static final String _TIME = "time";
        public static final String _CONTENT = "content";
        public static final String _TONAME = "toname";

        public static final String CREATE_SQL = "create table " +
                _TABLE + " (" +
                _ID + " integer primary key autoincrement," +
                _USERPIC + " text," +
                _USERNICKNAME + " text," +
                _TIME + " text" +
                _CONTENT + " text" +
                _TONAME + " );";
    }
}
