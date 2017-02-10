package com.example.williammontiel.willmontiel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.williammontiel.willmontiel.misc.JsonKeys;
import com.example.williammontiel.willmontiel.misc.WVClient;

public class WebViewActivity extends ActivityBase {

    protected WebView character_webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        character_webview = (WebView) findViewById(R.id.character_webview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String url = extras.getString(JsonKeys.CHARACTER_URLS_URL);
            character_webview.getSettings().setJavaScriptEnabled(true);
            character_webview.loadUrl(url);
            character_webview.setWebViewClient(new WVClient());
        }
    }
}
