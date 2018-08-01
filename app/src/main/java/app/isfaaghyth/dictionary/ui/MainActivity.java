package app.isfaaghyth.dictionary.ui;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.ArrayList;
import java.util.List;

import app.isfaaghyth.dictionary.R;
import app.isfaaghyth.dictionary.adapter.ListAdapter;
import app.isfaaghyth.dictionary.data.Dictionaries;
import app.isfaaghyth.dictionary.data.DictionaryManager;
import app.isfaaghyth.dictionary.data.repository.Suggestions;
import app.isfaaghyth.dictionary.data.repository.Words;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //@BindView(R.id.search_view) FloatingSearchView searchView;

    private FloatingSearchView searchView;

    private Dictionaries dictionaryManager;
    private List<Suggestions> suggestionses = new ArrayList<>();

    private boolean isIndonesia = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dictionaryManager = new DictionaryManager(this);
        dictionaryManager.open();

        //storeSuggestions();
        //initSearchView();
        //setupSearchViewPosition();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        dictionaryManager.close();
    }

    private void storeSuggestions() {
        suggestionses.clear();
        for (Words words: dictionaryManager.getAll(isIndonesia)) {
            suggestionses.add(new Suggestions(words.getWords()));
        }
    }

    private void initSearchView() {
        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override public void onSearchTextChanged(String oldQuery, String newQuery) {
                if (!oldQuery.isEmpty() && newQuery.isEmpty()) {
                    searchView.clearSuggestions();
                } else {
                    searchView.showProgress();
                    searchView.swapSuggestions(findWord(newQuery));
                    searchView.hideProgress();
                }
            }
        });
        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override public void onSuggestionClicked(SearchSuggestion item) {
                Suggestions suggestion = (Suggestions) item;
                String means = dictionaryManager.getByWord(isIndonesia, suggestion.getBody()).get(0).getMeans();
                Toast.makeText(getApplicationContext(), means, Toast.LENGTH_LONG).show();
            }
            @Override public void onSearchAction(String query) {}
        });
        searchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override public void onActionMenuItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mn_english:
                        isIndonesia = false;
                        break;
                    case R.id.mn_indonesia:
                        isIndonesia = true;
                        break;
                    default:
                        isIndonesia = true;
                }
                storeSuggestions();
            }
        });
    }

    private void setupSearchViewPosition() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) searchView.getLayoutParams();
        params.topMargin = ((height * 30) / 100);
        searchView.setLayoutParams(params);
    }

    private List<Suggestions> findWord(String query) {
        List<Suggestions> temp = new ArrayList<>();
        for (Suggestions suggestion: suggestionses) {
            if (suggestion.getBody().toLowerCase().contains(query)) {
                temp.add(suggestion);
            }
        }
        return temp;
    }
}
