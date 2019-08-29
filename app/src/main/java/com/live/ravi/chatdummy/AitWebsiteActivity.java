package com.live.ravi.chatdummy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class AitWebsiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ait_website);

        WebView webView = (WebView) findViewById(R.id.aitwebsite);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
       // webSettings.getSettings().setJavaScriptEnabled(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);

        webView.loadUrl("http://www.assureindia.in/index.php");
    }
}
