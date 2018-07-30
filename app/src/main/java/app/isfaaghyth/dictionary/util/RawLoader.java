package app.isfaaghyth.dictionary.util;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import app.isfaaghyth.dictionary.data.repository.Words;

/**
 * Created by isfaaghyth on 7/30/18.
 * github: @isfaaghyth
 */

public class RawLoader {

    private Context context;

    public RawLoader(Context context) {
        this.context = context;
    }

    public List<Words> load(int rawData) {
        List<Words> words = new ArrayList<>();
        String line;
        InputStream stream;
        BufferedReader reader;
        try {
            Resources res = context.getResources();
            stream = res.openRawResource(rawData);
            reader = new BufferedReader(new InputStreamReader(stream));
            do {
                line = reader.readLine();
                String[] split = line.split("\t");
                Words word = new Words.Builder()
                        .setWords(split[0])
                        .setMeans(split[1])
                        .build();
                words.add(word);
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }

}
