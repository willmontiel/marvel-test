package com.example.williammontiel.willmontiel;

import android.location.Location;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.example.williammontiel.willmontiel.adapters.MarvelAdapter;
import com.example.williammontiel.willmontiel.misc.VolleyErrorHandler;
import com.example.williammontiel.willmontiel.models.MarvelCharacter;
import com.example.williammontiel.willmontiel.resources.Cons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends ActivityBase {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.activity_main);
        progress = findViewById(R.id.main_progress);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        //mAdapter = new MarvelAdapter(getCharacters());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getCharacters() {
        showProgress(true, layout, progress);

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        // Instantiate the RequestQueue with the cache and network.
        RequestQueue mRequestQueue = new RequestQueue(cache, network);
        // Start the queue
        mRequestQueue.start();

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                Cons.GET_ALL_CHARACTERS,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LALA", response);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            validateErrorResponse(error);
                    }
                }) {
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                (int) TimeUnit.SECONDS.toMillis(10),//time out in 10second
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,//DEFAULT_MAX_RETRIES = 1;
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mRequestQueue.add(stringRequest);
    }

    /*
    private List<MarvelCharacter> getCharacters() {
        List items = new ArrayList();
        items.add(new MarvelCharacter(1, "Angel Beats", "", ""));
        items.add(new MarvelCharacter(2, "LALA", "", ""));
        items.add(new MarvelCharacter(3, "LOLO", "", ""));
        items.add(new MarvelCharacter(4, "LULU", "", ""));
        items.add(new MarvelCharacter(5, "JEJE", "", ""));

        return items;
    }
    */

    private void setRecyclerView() {

    }
}
