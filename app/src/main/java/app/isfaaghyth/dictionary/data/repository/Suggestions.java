package app.isfaaghyth.dictionary.data.repository;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by isfaaghyth on 8/1/18.
 * github: @isfaaghyth
 */

public class Suggestions implements SearchSuggestion {

    private String query;

    public Suggestions(String query) {
        this.query = query.toLowerCase();
    }

    @Override public String getBody() {
        return query;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {

    }

}
