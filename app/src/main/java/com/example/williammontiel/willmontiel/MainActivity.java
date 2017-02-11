package com.example.williammontiel.willmontiel;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
import com.example.williammontiel.willmontiel.deserializers.CharacterDeserializer;
import com.example.williammontiel.willmontiel.misc.EndlessRecyclerViewScrollListener;
import com.example.williammontiel.willmontiel.misc.JsonKeys;
import com.example.williammontiel.willmontiel.resources.Cons;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends ActivityBase {

    private EndlessRecyclerViewScrollListener scrollListener;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText search;
    public int offset = 0;

    public List items = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validateSession();

        layout = findViewById(R.id.recycler_view);
        progress = findViewById(R.id.main_progress);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getCharacters(offset, true);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void getCharacters(int page, final Boolean firstTime) {
        showProgress(true, layout, progress);

        String query = "";
        if (search != null && search.getText() != null && !TextUtils.isEmpty(search.getText())) {
            query = Cons.QUERY + search.getText();
        }

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        // Instantiate the RequestQueue with the cache and network.
        RequestQueue mRequestQueue = new RequestQueue(cache, network);
        // Start the queue
        mRequestQueue.start();

        //Log.d("LALA", "URL: " + Cons.URL_BASE + Cons.API + Cons.ALL_CHARACTERS  + Cons.APIKEY + Cons.MARVEL_DEVELOPER_APIKEY + Cons.HASH + Cons.MARVEL_DEVELOPER_HASH + Cons.OFFSET + page + Cons.TIMESTAMP + Cons.MARVEL_DEVELOPER_TIMESTAMP + query);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                Cons.URL_BASE + Cons.API + Cons.ALL_CHARACTERS  + Cons.APIKEY + Cons.MARVEL_DEVELOPER_APIKEY + Cons.HASH + Cons.MARVEL_DEVELOPER_HASH + Cons.OFFSET + page + Cons.TIMESTAMP + Cons.MARVEL_DEVELOPER_TIMESTAMP + query,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponseData(response, firstTime);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showProgress(false, layout, progress);
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


    public void processResponseData(String response, Boolean firstTime) {
        try {
            JSONObject resObj = new JSONObject(response);
            JSONObject dataObj = resObj.getJSONObject(JsonKeys.DATA);
            JSONArray chars = dataObj.getJSONArray(JsonKeys.RESULTS);

            if (chars.length() <= 0) {
                setErrorSnackBar(layout, Cons.NO_CHARACTERS);
            }
            else {
                CharacterDeserializer deserializer = new CharacterDeserializer();

                for (int i = 0; i < chars.length(); i++) {
                    JSONObject character = chars.getJSONObject(i);
                    deserializer.setJsonObject(character);
                    deserializer.deserialize();
                    items.add(deserializer.getCharacter());
                }

                if (firstTime) {
                    createRecyclerView();
                } else {
                    refreshData();
                }
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    private void createRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MarvelAdapter(items, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
        // Retain an instance so that you can call `resetState()` for fresh searches

        scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        mRecyclerView.addOnScrollListener(scrollListener);
        showProgress(false, layout, progress);
    }

    private void refreshData() {
        //int curSize = mAdapter.getItemCount();

// replace this line with wherever you get new records

// update the existing list
// curSize should represent the first element that got added
// newItems.size() represents the itemCount
        //mAdapter.notifyItemRangeInserted(curSize, items.size());
        //scrollListener.resetState();
        mAdapter.notifyDataSetChanged();
        showProgress(false, layout, progress);
    }

    public void loadNextDataFromApi(int offset) {
        getCharacters(offset, false);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        menu.findItem(R.id.user_email).setTitle(user.getEmail());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search:
                handleMenuSearch();
                return true;

            case R.id.user_email:
                return true;

            case R.id.logout:
                session.logoutUser();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(isSearchOpened) {
            handleMenuSearch();
            return;
        }
        super.onBackPressed();
    }

    protected void handleMenuSearch(){
        ActionBar action = getSupportActionBar(); //get the actionbar
        if(isSearchOpened){ //test if the search is open
            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar

            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(search.getWindowToken(), 0);

            //add the search icon in the action bar
            mSearchAction.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_search));

            search.setText(null);

            items.clear();
            scrollListener.resetState();
            getCharacters(offset, false);

            isSearchOpened = false;
        } else { //open the search entry
            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            action.setCustomView(R.layout.search_bar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title

            search = (EditText)action.getCustomView().findViewById(R.id.search); //the text editor

            //this is a listener to do a search when the user clicks on search button
            search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        items.clear();
                        scrollListener.resetState();
                        getCharacters(offset, false);
                        return true;
                    }
                    return false;
                }
            });

            search.requestFocus();

            //open the keyboard focused in the edtSearch
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(search, InputMethodManager.SHOW_IMPLICIT);


            //add the close icon
            mSearchAction.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_close_clear_cancel));

            isSearchOpened = true;
        }
    }
}
