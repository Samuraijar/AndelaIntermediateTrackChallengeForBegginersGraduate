package com.example.android.andelaintermediatetrackchallengeforbegginersgraduate;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static android.content.ContentValues.TAG;
import static java.lang.System.in;

/**
 * Created by NORMAL on 8/22/2017.
 */

public class JSONfunctions {
    private static final String TAG = JSONfunctions.class.getSimpleName();

    public static String getJSONfromURL(String url) {
        String response = null;

        try {
            URL theUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)theUrl.openConnection();
            conn.setRequestMethod("GET");

            InputStream is = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(is);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException:" + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException:" + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException:" + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception;" + e.getMessage());
        }
        return response;
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try{
            while ((line = reader.readLine()) != null ) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}

