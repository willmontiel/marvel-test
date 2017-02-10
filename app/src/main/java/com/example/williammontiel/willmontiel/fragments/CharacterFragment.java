package com.example.williammontiel.willmontiel.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.williammontiel.willmontiel.R;
import com.example.williammontiel.willmontiel.deserializers.CharacterDeserializer;
import com.example.williammontiel.willmontiel.misc.JsonKeys;
import com.example.williammontiel.willmontiel.models.MarvelCharacter;
import com.example.williammontiel.willmontiel.resources.Cons;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.TimeUnit;

public class CharacterFragment extends FragmentBase {

    protected String idChar;
    protected Context context;
    protected MarvelCharacter character;

    public CharacterFragment() {
        // Required empty public constructor
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static CharacterFragment newInstance(String id, Context context) {
        CharacterFragment fragment = new CharacterFragment();
        fragment.setContext(context);
        Bundle args = new Bundle();
        args.putString(JsonKeys.CHARACTER_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.idChar = getArguments().getString(JsonKeys.CHARACTER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_character, container, false);
        layout = v.findViewById(R.id.character_layout);
        progress = v.findViewById(R.id.character_progress);
        getCharacter();
        return v;
    }


    private void getCharacter() {
        showProgress(true, layout, progress);

        // Instantiate the cache
        Cache cache = new DiskBasedCache(this.context.getCacheDir(), 1024 * 1024); // 1MB cap
        // Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());
        // Instantiate the RequestQueue with the cache and network.
        RequestQueue mRequestQueue = new RequestQueue(cache, network);
        // Start the queue
        mRequestQueue.start();

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                Cons.URL_BASE + Cons.API + Cons.CHARACTER + idChar + Cons.APIKEY + Cons.MARVEL_DEVELOPER_APIKEY + Cons.HASH + Cons.MARVEL_DEVELOPER_HASH + Cons.OFFSET + Cons.TIMESTAMP + Cons.MARVEL_DEVELOPER_TIMESTAMP,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponseData(response);
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

    public void processResponseData(String response) {
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
                    this.character = deserializer.getCharacter();
                }

                
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

}
