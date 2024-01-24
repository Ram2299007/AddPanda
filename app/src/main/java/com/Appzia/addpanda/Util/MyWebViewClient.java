package com.Appzia.addpanda.Util;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

    private String targetUrl;

    public MyWebViewClient(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.equals(targetUrl)) {
            // Close the Chrome custom tab
            view.getContext().getPackageManager().getLaunchIntentForPackage("com.android.chrome").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(view.getContext().getPackageManager().getLaunchIntentForPackage("com.android.chrome"));
            return true; // Return true to indicate that the URL is handled
        }
        return false; // Allow the WebView to load other URLs
    }
}
