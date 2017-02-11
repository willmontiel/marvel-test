package com.example.williammontiel.willmontiel.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.williammontiel.willmontiel.R;
import com.example.williammontiel.willmontiel.holders.CharacterHolder;
import com.example.williammontiel.willmontiel.models.MarvelCharacter;

import java.util.List;

/**
 * Created by william.montiel on 09/02/2017.
 */

public class MarvelAdapter extends RecyclerView.Adapter<CharacterHolder> {
    private List<MarvelCharacter> items;
    protected Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MarvelAdapter(List<MarvelCharacter> items, Context context) {
        this.items = items;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CharacterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new CharacterHolder(v, this.context);
    }

    @Override
    public void onBindViewHolder(final CharacterHolder holder, int position) {
        MarvelCharacter character = this.items.get(position);
        holder.setCharacter(character);
        holder.setSummaryData();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }
}