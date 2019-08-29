package com.live.ravi.chatdummy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class PdfviewActivity extends AppCompatActivity {
    private String pdflink;
    TextView edt;
    WebView webview;
    ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        pdflink = getIntent().getStringExtra("pdflink");


        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);

        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);

        String filename = "http://www3.nd.edu/~cpoellab/teaching/cse40816/android_tutorial.pdf";
        webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + pdflink + ".pdf");

        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                progressbar.setVisibility(View.GONE);
            }
        });
    }

        }




