package com.example.annaroxas.teamdiscovery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Owner on 22/05/2017.
 */

public class SuccessLogin extends AppCompatActivity {
    private static final String TAG = SuccessLogin.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState == null){
            Log.d (TAG, "savedInstanceState fucked right up");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success_activity);
        initImages();

    }

    private void initImages(){
        ImageView boy_view = (ImageView) findViewById(R.id.cc_child_avtr);
        Glide.with(this)
                .load(R.drawable.boy)
                .into(boy_view);

        ImageButton setting_button = (ImageButton) findViewById(R.id.cc_sett);
        Glide.with(this)
                .load(R.drawable.settings)
                .fitCenter()
                .into(setting_button);

        ImageButton info_button = (ImageButton) findViewById(R.id.cc_info);
        Glide.with(this)
                .load(R.drawable.about)
                .fitCenter()
                .into(info_button);

        ImageButton addchild_button = (ImageButton) findViewById(R.id.cc_add_child);
        Glide.with(this)
                .load(R.drawable.cc_addnew)
                .fitCenter()
                .into(addchild_button);

        ImageButton ok_button = (ImageButton) findViewById(R.id.cc_ok);
        Glide.with(this)
                .load(R.drawable.cc_ok)
                .fitCenter()
                .into(ok_button);

        ImageButton logout_button = (ImageButton) findViewById(R.id.cc_logout);
        Glide.with(this)
                .load(R.drawable.cc_logout)
                .fitCenter()
                .into(logout_button);



    }
}