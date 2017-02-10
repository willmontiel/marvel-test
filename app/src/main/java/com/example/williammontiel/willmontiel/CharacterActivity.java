package com.example.williammontiel.willmontiel;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
import com.example.williammontiel.willmontiel.fragments.CharacterFragment;
import com.example.williammontiel.willmontiel.misc.JsonKeys;
import com.example.williammontiel.willmontiel.models.MarvelCharacter;
import com.example.williammontiel.willmontiel.resources.Cons;

import java.util.concurrent.TimeUnit;

public class CharacterActivity extends ActivityBase {

    protected CharacterFragment characterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String id = extras.getString(JsonKeys.CHARACTER_ID);
            setFragment(id);
        }
    }

    protected void setFragment(String id) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        characterFragment = CharacterFragment.newInstance(id, getApplicationContext());
        fragmentTransaction.add(R.id.fragment_container, characterFragment, "Services List Fragment");
        fragmentTransaction.commit();
    }

}
