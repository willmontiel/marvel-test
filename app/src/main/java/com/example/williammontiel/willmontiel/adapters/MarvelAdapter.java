package com.example.williammontiel.willmontiel.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.williammontiel.willmontiel.R;
import com.example.williammontiel.willmontiel.holders.CharacterHolder;
import com.example.williammontiel.willmontiel.misc.JsonKeys;
import com.example.williammontiel.willmontiel.models.MarvelCharacter;
import com.example.williammontiel.willmontiel.resources.Cons;

import org.json.JSONException;
import org.json.JSONObject;

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

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final CharacterHolder holder, int position) {
        MarvelCharacter character = this.items.get(position);
        holder.setCharacter(character);
        holder.setData();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }
}