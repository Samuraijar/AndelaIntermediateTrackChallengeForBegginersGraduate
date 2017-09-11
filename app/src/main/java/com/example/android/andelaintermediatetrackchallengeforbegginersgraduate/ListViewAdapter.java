package com.example.android.andelaintermediatetrackchallengeforbegginersgraduate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static android.R.attr.data;
import static android.R.attr.name;
import static android.media.CamcorderProfile.get;

/**
 * Created by NORMAL on 8/22/2017.
 */

public class ListViewAdapter extends BaseAdapter {
    //declaring variables

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> githubUsersInfo;
    ImageLoader imageLoader;


    public ListViewAdapter (Context context,
                            ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        githubUsersInfo = arraylist;
        imageLoader = new ImageLoader (context);
    }
    @Override
    public int getCount () {
        return githubUsersInfo.size();
    }
    @Override
    public  Object getItem(int position) {
        return  null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView login;
        TextView html_url;
        ImageView avatar_url;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.listview_item, parent, false);

        HashMap<String, String> resultp = new HashMap<String, String>();

        resultp = githubUsersInfo.get(position);

        login = (TextView) itemView.findViewById(R.id.login);
        html_url = (TextView) itemView.findViewById(R.id.html_url);
        avatar_url = (ImageView) itemView.findViewById(R.id.avatar_url);

        login.setText(resultp.get(MainActivity.NAME));
        html_url.setText(resultp.get(MainActivity.URL));
        imageLoader.DisplayImage(resultp.get(MainActivity.IMAGE), avatar_url);



        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> resultp = new HashMap<String, String>();
                resultp = githubUsersInfo.get(position);
                Intent intent = new Intent(context, SingleItemView.class);

                intent.putExtra("login", resultp.get(MainActivity.NAME));
                intent.putExtra("html_url", resultp.get(MainActivity.URL));
                intent.putExtra("avatar_url", resultp.get(MainActivity.IMAGE));
                context.startActivity(intent);

            }
        });
        return itemView;
    }
}
