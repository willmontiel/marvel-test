package com.example.williammontiel.willmontiel.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.williammontiel.willmontiel.R;
import com.example.williammontiel.willmontiel.models.MarvelCharacter;

import java.util.List;

/**
 * Created by william.montiel on 09/02/2017.
 */

public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.ViewHolder> {
    private List<MarvelCharacter> items;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView id;
        public TextView name;
        public ImageView imageUrl;

        public ViewHolder(View v) {
            super(v);
            id = (TextView) v.findViewById(R.id.id);
            name = (TextView) v.findViewById(R.id.name);
            imageUrl = (ImageView) v.findViewById(R.id.imageUrl);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MarvelAdapter(List<MarvelCharacter> items) {
        this.items = items;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MarvelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.id.setText("" + this.items.get(position).getId());
        holder.name.setText("Name: " + String.valueOf(this.items.get(position).getName()));
        //holder.imageUrl.setImageResource(items.get(position).getImageUrl());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }
}