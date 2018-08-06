package app.isfaaghyth.dictionary.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.List;

import app.isfaaghyth.dictionary.R;
import app.isfaaghyth.dictionary.adapter.WordAdapter;
import app.isfaaghyth.dictionary.data.Dictionaries;
import app.isfaaghyth.dictionary.data.DictionaryManager;
import app.isfaaghyth.dictionary.data.repository.Words;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lst_dictionary) FastScrollRecyclerView lstDictionary;

    private LinearLayoutManager layoutManager;
    private WordAdapter adapter;
    private List<Words> words;
    private Dictionaries dictionaryManager;
    private boolean isIndonesia = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dictionaryManager = new DictionaryManager(this);
        dictionaryManager.open();

        words = dictionaryManager.getAll(isIndonesia);

        layoutManager = new LinearLayoutManager(this);
        lstDictionary.setLayoutManager(layoutManager);

        adapter = new WordAdapter(words);
        lstDictionary.setAdapter(adapter);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        dictionaryManager.close();
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        SearchView searchMovieView = (SearchView) menu.findItem(R.id.mn_search).getActionView();
        searchMovieView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                words.clear();
                words.addAll(dictionaryManager.getByWord(isIndonesia, query));
                adapter.notifyDataSetChanged();
                return false;
            }
            @Override public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    getDefaultData();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
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
        getDefaultData();
        return super.onOptionsItemSelected(item);
    }

    private void getDefaultData() {
        words.clear();
        words.addAll(dictionaryManager.getAll(isIndonesia));
        adapter.notifyDataSetChanged();
    }
}
