package app.isfaaghyth.dictionary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.isfaaghyth.dictionary.R;

/**
 * Created by isfaaghyth on 7/28/18.
 * github: @isfaaghyth
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {

    private List<String> items;

    public ListAdapter(List<String> items) {
        this.items = items;
    }

    @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dictionary, parent, false));
    }

    @Override public void onBindViewHolder(Holder holder, int position) {
        holder.txtTitle.setText(items.get(position));
    }

    @Override public int getItemCount() {
        return items.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView txtTitle;

        Holder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
        }
    }
}
