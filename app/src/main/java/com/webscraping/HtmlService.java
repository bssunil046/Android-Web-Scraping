package com.webscraping;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class HtmlService extends IntentService {
    public static final String ACTION_HTML_SOURCE = "com.webscraping.action_html";
    public static final String HTML_SOURCE = "htmlSource";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public HtmlService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            String htmlSource = Util.getHtmlSource1(intent.getStringExtra("url"));
            Log.i("HtmlService", htmlSource);

            Intent i = new Intent(ACTION_HTML_SOURCE);
            i.putExtra(HTML_SOURCE, htmlSource);
            LocalBroadcastManager.getInstance(this).sendBroadcast(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
