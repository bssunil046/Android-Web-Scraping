package com.webscraping;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Util {
    public static String getHtmlSource(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String input = null;
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            try {
                if (!((input = in.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            stringBuffer.append(input);
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("Util", stringBuffer.toString());
        return stringBuffer.toString();
    }

    public static String getHtmlSource1(String url) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        String input;
        StringBuffer stringBuffer = new StringBuffer();
        while ((input = in.readLine()) != null) {
            stringBuffer.append(input);
        }
        in.close();
        String htmlData = stringBuffer.toString();

        return htmlData;
    }
}
