package app.isfaaghyth.dictionary.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import app.isfaaghyth.dictionary.data.helper.DataHelper;
import app.isfaaghyth.dictionary.data.helper.DatabaseContract;
import app.isfaaghyth.dictionary.data.repository.Words;

/**
 * Created by isfaaghyth on 7/30/18.
 * github: @isfaaghyth
 */

public class DictionaryManager implements Dictionaries {

    private DataHelper dbHelper;
    private Context context;

    private String ID = DatabaseContract.TABLE_ID;
    private String ENG = DatabaseContract.TABLE_ENG;

    private SQLiteDatabase database;

    public DictionaryManager(Context context) {
        this.context = context;
    }

    public DictionaryManager open() {
        dbHelper = new DataHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    @Override public void bulkInsert(boolean isIndonesia, List<Words> words) {

    }

    @Override public List<Words> getAll() {
        return null;
    }

    @Override public List<Words> getByWord(boolean isIndonesia, String query) {
        return null;
    }

}
