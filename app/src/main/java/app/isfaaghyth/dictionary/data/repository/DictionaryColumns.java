package app.isfaaghyth.dictionary.data.repository;

import android.provider.BaseColumns;

/**
 * Created by isfaaghyth on 7/30/18.
 * github: @isfaaghyth
 */

public class DictionaryColumns implements BaseColumns {

    private static final String WORDS = "word";
    private static final String MEANS = "means";

    public static String getWords() {
        return WORDS;
    }

    public static String getMeans() {
        return MEANS;
    }

}
