package app.isfaaghyth.dictionary.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.util.List;

import app.isfaaghyth.dictionary.R;
import app.isfaaghyth.dictionary.data.repository.Words;

/**
 * Created by isfaaghyth on 7/28/18.
 * github: @isfaaghyth
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.Holder>
        implements FastScrollRecyclerView.SectionedAdapter {

    private List<Words> items;

    public WordAdapter(List<Words> items) {
        this.items = items;
    }

    @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dictionary, parent, false));
    }

    @Override public void onBindViewHolder(Holder holder, int position) {
        String word = items.get(position).getWords();
        String mean = items.get(position).getMeans();
        holder.txtFirstLetter.setText(String.valueOf(word.charAt(0)).toUpperCase());
        holder.txtWord.setText(word);
        holder.txtMean.setText(mean);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    @NonNull @Override public String getSectionName(int position) {
        return String.valueOf(items.get(position).getWords().charAt(0));
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView txtFirstLetter, txtWord, txtMean;

        Holder(View itemView) {
            super(itemView);
            txtFirstLetter = itemView.findViewById(R.id.txt_first_letter);
            txtWord = itemView.findViewById(R.id.txt_word);
            txtMean = itemView.findViewById(R.id.txt_mean);
        }
    }
}
