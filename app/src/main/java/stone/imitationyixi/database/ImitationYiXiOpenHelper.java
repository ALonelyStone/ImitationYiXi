package stone.imitationyixi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import stone.imitationyixi.common.DatabaseConstant;

/**
 * @author stone
 */

public class ImitationYiXiOpenHelper extends SQLiteOpenHelper {

    public ImitationYiXiOpenHelper(Context context) {
        super(context, DatabaseConstant.DB_FILE_NAME, null, DatabaseConstant.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseConstant.lecturesList.CREATE_SQL);
        db.execSQL(DatabaseConstant.UserDataList.CREATE_SQL);
        db.execSQL(DatabaseConstant.CommentsUsersList.CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
