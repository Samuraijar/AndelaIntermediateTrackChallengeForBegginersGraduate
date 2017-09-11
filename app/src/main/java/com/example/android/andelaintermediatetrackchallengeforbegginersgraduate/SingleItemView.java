package com.example.android.andelaintermediatetrackchallengeforbegginersgraduate;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * Created by NORMAL on 8/23/2017.
 */

public class SingleItemView extends Activity {
    String login;
    String html_url;
    String avatar_url;
    String position;
    ImageLoader imageLoader = new ImageLoader(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);

        Intent i = getIntent();
        login = i.getStringExtra("login");
        html_url = i.getStringExtra("html_url");
        avatar_url = i.getStringExtra("avatar_url");

        TextView txtlogin = (TextView) findViewById(R.id.login);
        TextView txthtml_url = (TextView) findViewById(R.id.html_url);

        ImageView imgavatar_url = (ImageView) findViewById(R.id.avatar_url);

        txtlogin.setText(login);
        txthtml_url.setText(html_url);

        imageLoader.DisplayImage(avatar_url, imgavatar_url);
    }
}
