package app.isfaaghyth.dictionary.data.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.isfaaghyth.dictionary.data.repository.DictionaryColumns;

/**
 * Created by isfaaghyth on 7/30/18.
 * github: @isfaaghyth
 */

public class DataHelper extends SQLiteOpenHelper {

    public DataHelper(Context context){
        super(context, DatabaseContract.DB_NAME, null, DatabaseContract.DB_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(doCreateDatabase(DatabaseContract.TABLE_ID));
        db.execSQL(doCreateDatabase(DatabaseContract.TABLE_ENG));
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropDatabase(DatabaseContract.TABLE_ID));
        db.execSQL(dropDatabase(DatabaseContract.TABLE_ENG));
        onCreate(db);
    }

    private String doCreateDatabase(String tableName) {
        return DatabaseContract.createTable(tableName);
    }

    private String dropDatabase(String tableName) {
        return DatabaseContract.dropTable(tableName);
    }

}
