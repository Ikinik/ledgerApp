package com.ledgerApp.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.*;

public class MainActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.activity_main_webview);

        // Force links and redirects to open in the WebView instead of in a browser
        //mWebView.setWebViewClient(new WebViewClient());

        //adjusted by me
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // REMOTE RESOURCE
        mWebView.loadUrl("https://ledger.sorfa.eu");
        mWebView.setWebViewClient(new MyWebViewClient());

        // LOCAL RESOURCE
        // mWebView.loadUrl("file:///android_asset/index.html");
    }

    // Prevent the back-button from closing the app
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}