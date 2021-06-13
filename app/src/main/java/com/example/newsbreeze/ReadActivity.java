package com.example.newsbreeze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ReadActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        Intent intent = getIntent();
        String newsUrl = intent.getStringExtra("key"); //if it's a string you stored


        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(newsUrl);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack())
        {
            webView.goBack();
        }
        else
        super.onBackPressed();
    }
}