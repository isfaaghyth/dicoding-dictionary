package app.isfaaghyth.dictionary.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import app.isfaaghyth.dictionary.data.helper.DataHelper;
import app.isfaaghyth.dictionary.data.helper.DatabaseContract;
import app.isfaaghyth.dictionary.data.repository.DictionaryColumns;
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

    public SQLiteDatabase getDatabase() {
        return database;
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
        String currentTable = isIndonesia ? ID : ENG;
        beginTransaction();
        SQLiteStatement statement = database.compileStatement(DatabaseContract.insertData(currentTable));
        for (Words word: words) {
            statement.bindString(1, word.getWords());
            statement.bindString(2, word.getMeans());
            statement.execute();
            statement.clearBindings();
        }
        setTransactionSuccess();
        endTransaction();
    }

    @Override public List<Words> getAll(boolean isIndonesia) {
        String currentTable = isIndonesia ? ID : ENG;
        return doQueries(DatabaseContract.getAllData(currentTable));
    }

    @Override public List<Words> getByWord(boolean isIndonesia, String query) {
        String currentTable = isIndonesia ? ID : ENG;
        return doQueries(DatabaseContract.selectByQuery(currentTable, query));
    }

    @Override public List<Words> doQueries(String query) {
        Cursor cursor = getDatabase().rawQuery(query, null);
        List<Words> words = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Words.Builder word = new Words.Builder();
                word.setId(cursor.getInt(cursor.getColumnIndex(DictionaryColumns._ID)));
                word.setWords(cursor.getString(cursor.getColumnIndex(DictionaryColumns.getWords())));
                word.setMeans(cursor.getString(cursor.getColumnIndex(DictionaryColumns.getMeans())));
                words.add(word.build());
            } while (cursor.moveToNext());
        }
        cursor.close();
        return words;
    }
}
