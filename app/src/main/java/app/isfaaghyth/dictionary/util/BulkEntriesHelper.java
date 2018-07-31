package app.isfaaghyth.dictionary.util;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import app.isfaaghyth.dictionary.R;
import app.isfaaghyth.dictionary.data.DictionaryManager;
import app.isfaaghyth.dictionary.data.repository.Words;
import app.isfaaghyth.dictionary.ui.MainActivity;
import io.isfaaghyth.rak.Rak;

/**
 * Created by isfaaghyth on 7/30/18.
 * github: @isfaaghyth
 */

public class BulkEntriesHelper extends AsyncTask<Void, Integer, Void> {

    private DictionaryManager dictionary;
    private RawLoader rawLoader;

    private Activity activity;

    public BulkEntriesHelper(Activity context) {
        this.activity = context;
    }

    @Override protected void onPreExecute() {
        super.onPreExecute();
        dictionary = new DictionaryManager(activity);
        rawLoader = new RawLoader(activity);
    }

    @Override protected Void doInBackground(Void... params) {
        dictionary.open();
        List<Words> indonesiaLang = rawLoader.load(R.raw.indonesia_english);
        List<Words> englishLang = rawLoader.load(R.raw.english_indonesia);
        dictionary.bulkInsert(true, indonesiaLang);
        dictionary.bulkInsert(false, englishLang);
        dictionary.close();
        Rak.entry("init", true);
        return null;
    }

    @Override protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.finish();
    }

}