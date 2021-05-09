package com.webscraping;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.webscraping.api.ApiClient;
import com.webscraping.api.ApiInterface;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private WebView webView;
    private TextView webViewHtmlSource;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        url = getIntent().getStringExtra("url");

        webView = findViewById(R.id.webView);
        webViewHtmlSource = findViewById(R.id.webViewSource);

        webView.loadUrl(url);

        ApiInterface apiInterface = ApiClient.getClient(this, url).create(ApiInterface.class);
        Call<String> call = apiInterface.url();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("MainActivity", response.body());
                webViewHtmlSource.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("MainActivity", "on failure");

            }
        });
    }
}