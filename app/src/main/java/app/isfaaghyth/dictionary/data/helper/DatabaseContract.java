package app.isfaaghyth.dictionary.data.helper;

import app.isfaaghyth.dictionary.data.repository.DictionaryColumns;

/**
 * Created by isfaaghyth on 7/30/18.
 * github: @isfaaghyth
 */

public class DatabaseContract {

    static final String DB_NAME = "dictionary.db";
    static final int DB_VERSION = 1;

    public static final String TABLE_ID = "indonesia_words";
    public static final String TABLE_ENG = "english_words";

    public DatabaseContract() {}

    static String createTable(String tableName) {
        return "CREATE TABLE " + tableName + " (" +
                DictionaryColumns._ID + " INTEGER PRIMARY KEY," +
                DictionaryColumns.getWords() + " TEXT," +
                DictionaryColumns.getMeans() + " TEXT)";
    }

    static String dropTable(String tableName) {
        return "DROP TABLE IF EXISTS " + tableName;
    }

    public static String insertData(String tableName) {
        return "INSERT INTO " + tableName + " (" +
                DictionaryColumns.getWords() + ", " +
                DictionaryColumns.getMeans() + ") VALUES (?, ?)";
    }

    public static String getAllData(String tableName) {
        return "SELECT * FROM "+ tableName +" ORDER BY " +
                DictionaryColumns.getWords() +" ASC";
    }

    public static String selectByQuery(String tableName, String query) {
        return "SELECT * FROM "+ tableName +" WHERE " + DictionaryColumns.getWords()
                +" LIKE '%" + query.trim() + "%'";
    }

}
