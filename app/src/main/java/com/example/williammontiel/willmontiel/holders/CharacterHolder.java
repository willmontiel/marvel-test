package com.example.williammontiel.willmontiel.holders;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.example.williammontiel.willmontiel.CharacterActivity;
import com.example.williammontiel.willmontiel.R;
import com.example.williammontiel.willmontiel.misc.JsonKeys;
import com.example.williammontiel.willmontiel.models.MarvelCharacter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Will Montiel on 02/09/2017.
 */

// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
public class CharacterHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    protected Context context;
    protected MarvelCharacter character;
    public TextView id;
    public TextView name;
    final public ImageView thumbnail;

    public CharacterHolder(View v, Context context) {
        super(v);
        this.context = context;
        name = (TextView) v.findViewById(R.id.name);
        thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
        characterDetails();
    }

    public void setCharacter(MarvelCharacter character) {
        this.character = character;
    }

    public void setData() {
        name.setText(character.getName());
        final JSONObject thumbnailData = character.getThumbnail();

        String durl = "";
        try {
            durl = thumbnailData.get(JsonKeys.CHARACTER_THUMBNAIL_PATH) + "/" + JsonKeys.CHARACTER_THUMBNAIL_RATIO_PORTRAIT_MEDIUM + "." + thumbnailData.getString(JsonKeys.CHARACTER_THUMBNAIL_EXT);
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }

        // Instantiate the cache
        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024); // 1MB cap
        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        // Instantiate the RequestQueue with the cache and network.
        RequestQueue mRequestQueue = new RequestQueue(cache, network);
        // Start the queue
        mRequestQueue.start();

        ImageRequest drequest = new ImageRequest(durl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        thumbnail.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        thumbnail.setImageResource(R.drawable.not_found);
                    }
                });

        mRequestQueue.add(drequest);
    }

    public void characterDetails() {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CharacterActivity.class);
                i.putExtra(JsonKeys.CHARACTER_ID, "" + character.getId());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }
}
