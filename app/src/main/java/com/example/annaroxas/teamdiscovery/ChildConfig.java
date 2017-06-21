package com.example.annaroxas.teamdiscovery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Owner on 20/06/2017.
 */

public class ChildConfig extends AppCompatActivity {
    private static final String TAG = ChildConfig.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            Log.d(TAG, "savedInstanceState DALE fucked right up");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_config_activity);
        initImages();

    }


    private void initImages(){


        ImageView image_view_ten = (ImageView) findViewById(R.id.imageView10);
        Glide.with(this)
                .load(R.drawable.childid)
                .fitCenter()
                .into(image_view_ten);

        ImageView image_view_eleven = (ImageView) findViewById(R.id.imageView11);
        Glide.with(this)
                .load(R.drawable.newreport)
                .fitCenter()
                .into(image_view_eleven);

        ImageView image_view_fourteen = (ImageView) findViewById(R.id.imageView14);
        Glide.with(this)
                .load(R.drawable.newrewards)
                .fitCenter()
                .into(image_view_fourteen);

        ImageView image_view_thirteen = (ImageView) findViewById(R.id.imageView13);
        Glide.with(this)
                .load(R.drawable.children)
                .fitCenter()
                .into(image_view_thirteen);

        ImageView image_view_twelve = (ImageView) findViewById(R.id.imageView12);
        Glide.with(this)
                .load(R.drawable.guardian)
                .fitCenter()
                .into(image_view_twelve);

        ImageButton update_button = (ImageButton) findViewById(R.id.cc_ok);
        Glide.with(this)
                .load(R.drawable.update)
                .fitCenter()
                .into(update_button);

        ImageButton avatar_button = (ImageButton) findViewById(R.id.chc_imgbtn_avatar);
        Glide.with(this)
                .load(R.drawable.boy)
                .fitCenter()
                .into(avatar_button);

        ImageButton setting_button = (ImageButton) findViewById(R.id.cc_sett);
        Glide.with(this)
                .load(R.drawable.settings)
                .fitCenter()
                .into(setting_button);

        ImageButton aboot_button = (ImageButton) findViewById(R.id.cc_info); //Eh?
        Glide.with(this)
                .load(R.drawable.about)
                .fitCenter()
                .into(aboot_button); //Eh?
    }
}
