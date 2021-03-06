package com.example.williammontiel.willmontiel.holders;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.williammontiel.willmontiel.CharacterActivity;
import com.example.williammontiel.willmontiel.R;
import com.example.williammontiel.willmontiel.WebViewActivity;
import com.example.williammontiel.willmontiel.misc.JsonKeys;
import com.example.williammontiel.willmontiel.models.MarvelCharacter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    public TextView description;
    public TextView comicsSeries;

    public ListView comics_list;
    public TextView total_comics;
    public ListView series_list;
    public TextView total_series;
    public ListView events_list;
    public TextView total_events;

    public TextView comics;
    public TextView more;
    final public ImageView thumbnail;

    public CharacterHolder(View v, Context context) {
        super(v);
        this.context = context;
        name = (TextView) v.findViewById(R.id.name);
        description = (TextView) v.findViewById(R.id.description);
        thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
    }

    public void setCharacter(MarvelCharacter character) {
        this.character = character;
    }

    public void setSummaryData() {
        name.setText(this.character.getName());
        comicsSeries = (TextView) itemView.findViewById(R.id.comicsSeries);
        comicsSeries.setText("Este personaje participa en " + this.character.getTotalComics() + " comics y " + this.character.getTotalSeries() + " series.");

        setThumbnail(getUrlThumbnail(JsonKeys.CHARACTER_THUMBNAIL_RATIO_PORTRAIT_MEDIUM ));
        characterDetails();
    }

    public ArrayList<String> extractData(List<JSONObject> objects) {
        ArrayList<String> comicsList = new ArrayList<String>();

        try {
            List<JSONObject> comicsObj = objects;
            for (int i = 0; i < comicsObj.size(); i++) {
                JSONObject comic = comicsObj.get(i);
                comicsList.add(comic.getString(JsonKeys.CHARACTER_COMICS_NAME));
            }
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }

        return comicsList;
    }

    public void setDetailsData() {
        name.setText(this.character.getName());
        description.setText(this.character.getDescription());
        more = (TextView) itemView.findViewById(R.id.more);

        List<JSONObject> urls = this.character.getUrls();
        JSONObject url = urls.get(0);

        try {
            final String durl = url.getString(JsonKeys.CHARACTER_URLS_URL);
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, WebViewActivity.class);
                    i.putExtra(JsonKeys.CHARACTER_URLS_URL, durl);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        total_comics = (TextView) itemView.findViewById(R.id.total_comics);
        comics_list = (ListView) itemView.findViewById(R.id.comics_list);
        total_series = (TextView) itemView.findViewById(R.id.total_series);
        series_list = (ListView) itemView.findViewById(R.id.series_list);
        total_events = (TextView) itemView.findViewById(R.id.total_events);
        events_list = (ListView) itemView.findViewById(R.id.events_list);


        Resources r = this.context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics());
        int pxPerLine = Math.round(px);

        total_comics.setText("Este personaje aparece en " + this.character.getTotalComics() + " comics diferentes");
        comics_list.setAdapter(new ArrayAdapter<>(this.context, R.layout.list_item, extractData(this.character.getComics())));
        comics_list.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, this.character.getComics().size() * pxPerLine));


        total_series.setText("Este personaje aparece en " + this.character.getTotalSeries() + " series diferentes");
        series_list.setAdapter(new ArrayAdapter<>(this.context, R.layout.list_item, extractData(this.character.getSeries())));
        series_list.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, this.character.getSeries().size() * pxPerLine));

        total_events.setText("Este personaje aparece en " + this.character.getTotalEvents() + " eventos diferentes");
        events_list.setAdapter(new ArrayAdapter<>(this.context, R.layout.list_item, extractData(this.character.getEvents())));
        events_list.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, this.character.getEvents().size() * pxPerLine));

        setThumbnail(getUrlThumbnail(JsonKeys.CHARACTER_THUMBNAIL_RATIO_LANDSCAPE_INCREDIBLE ));
    }

    public String getUrlThumbnail(String size) {
        final JSONObject thumbnailData = this.character.getThumbnail();
        String durl = "";
        try {
            durl = thumbnailData.get(JsonKeys.CHARACTER_THUMBNAIL_PATH) + "/" + size + "." + thumbnailData.getString(JsonKeys.CHARACTER_THUMBNAIL_EXT);
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }

        return durl;
    }

    public void setThumbnail(String url) {
        Glide.with(this.context).load(url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(thumbnail);
        /*
        // Instantiate the cache
        Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024); // 1MB cap
        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        // Instantiate the RequestQueue with the cache and network.
        RequestQueue mRequestQueue = new RequestQueue(cache, network);
        // Start the queue
        mRequestQueue.start();

        ImageRequest drequest = new ImageRequest(url,
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
        */
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
