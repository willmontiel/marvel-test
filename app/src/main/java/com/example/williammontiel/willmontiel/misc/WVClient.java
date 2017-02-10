package com.example.williammontiel.willmontiel.misc;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Will Montiel on 02/10/2017.
 */

public class WVClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}