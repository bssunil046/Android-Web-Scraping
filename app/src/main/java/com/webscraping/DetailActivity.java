package com.webscraping;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.webscraping.viewmodel.HtmlViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class DetailActivity extends AppCompatActivity {
    private WebView webView;
    private TextView webViewHtmlSource;

    private String url;
    private HtmlViewModel htmlViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        htmlViewModel = new ViewModelProvider(this).get(HtmlViewModel.class);
        htmlViewModel.init();

        url = getIntent().getStringExtra("url");

        webView = findViewById(R.id.webView);
        webViewHtmlSource = findViewById(R.id.webViewSource);

        webView.loadUrl(url);
        htmlViewModel.getHtmlSource(url);
        htmlViewModel.getHtmlSourceLiveData().observe(this, (s) -> webViewHtmlSource.setText(s));
    }
}