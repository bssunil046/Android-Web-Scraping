package com.webscraping;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.webscraping.api.ApiClient;
import com.webscraping.api.ApiInterface;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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

//        Intent intent = new Intent(this, HtmlService.class);
//        intent.putExtra("url", url);
//        startService(intent);
        ApiInterface apiInterface = ApiClient.getClient(this, url).create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.url();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("MainActivity", response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("MainActivity", "on failure");

            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        LocalBroadcastManager.getInstance(this).registerReceiver((htmlSourceReceiver),
//                new IntentFilter(HtmlService.ACTION_HTML_SOURCE));
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(htmlSourceReceiver);
//    }

//    private final BroadcastReceiver htmlSourceReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if(intent.getAction().equals(HtmlService.ACTION_HTML_SOURCE)) {
//                webViewHtmlSource.setText(intent.getStringExtra(HtmlService.HTML_SOURCE));
//            }
//        }
//    };
}