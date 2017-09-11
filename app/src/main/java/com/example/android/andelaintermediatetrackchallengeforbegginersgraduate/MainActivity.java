package com.example.android.andelaintermediatetrackchallengeforbegginersgraduate;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    JSONObject githubUsersInfo;
    JSONArray githubUsersArray;
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressdialog;
    ArrayList<HashMap<String, String>> githubUsersList;
    static String NAME = "login";
    static String URL = "html_url";
    static String IMAGE = "avatar_url";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        new DownloadJSON().execute();
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressdialog = new ProgressDialog(MainActivity.this);
            mProgressdialog.setTitle("Java Developers In Lagos");
            mProgressdialog.setMessage("Loading");
            mProgressdialog.setIndeterminate(false);
            mProgressdialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            githubUsersList = new ArrayList<HashMap<String, String>>();
            String info = JSONfunctions.getJSONfromURL("https://api.github.com/search/users?q=language:java%20location:lagos");

            try {
                JSONObject githubUsersInfo = new JSONObject(info);
                //locating array node in JSON
                githubUsersArray = githubUsersInfo.getJSONArray("items");

                //Iterating through array to find the objects

                for (int i = 0; i < githubUsersArray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    githubUsersInfo = githubUsersArray.getJSONObject(i);

                    //retrieving objects under the array

                    map.put("login", githubUsersInfo.getString("login"));
                    map.put("html_url", githubUsersInfo.getString("html_url"));
                    map.put("avatar_url", githubUsersInfo.getString("avatar_url"));

                    githubUsersList.add(map);
                }

            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;


        }
        @Override
        protected void onPostExecute(Void args) {
            //calling list adapter on result
            listview = (ListView) findViewById(R.id.listview);
            adapter = new ListViewAdapter(MainActivity.this, githubUsersList);
            listview.setAdapter(adapter);
            mProgressdialog.dismiss();
        }
    }
}
