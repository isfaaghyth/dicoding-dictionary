package app.isfaaghyth.dictionary.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.ArrayList;
import java.util.List;

import app.isfaaghyth.dictionary.R;
import app.isfaaghyth.dictionary.adapter.ListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.search_view) FloatingSearchView searchView;
    @BindView(R.id.lst_result) RecyclerView lstResult;

    private List<String> items;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initSearchView();

        //setup rv
        lstResult.setLayoutManager(new LinearLayoutManager(this));

        //data collections
        items = new ArrayList<>();
        items.add("Saya");
        items.add("Mau");
        items.add("Makan");
        items.add("Nasi");
        items.add("Uduk");

        //setup adapter
        adapter = new ListAdapter(items);
        lstResult.setAdapter(adapter);
    }

    private void initSearchView() {
        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override public void onSearchTextChanged(String oldQuery, String newQuery) {
                Toast.makeText(MainActivity.this, oldQuery + "\n" + newQuery, Toast.LENGTH_LONG).show();
            }
        });
    }
}
