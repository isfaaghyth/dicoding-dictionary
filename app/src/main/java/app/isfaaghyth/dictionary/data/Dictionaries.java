package app.isfaaghyth.dictionary.data;

import java.util.List;

import app.isfaaghyth.dictionary.data.repository.Words;

/**
 * Created by isfaaghyth on 7/30/18.
 * github: @isfaaghyth
 */

public interface Dictionaries {
    void bulkInsert(boolean isIndonesia, List<Words> words);
    List<Words> getAll();
    List<Words> getByWord(boolean isIndonesia, String query);
}