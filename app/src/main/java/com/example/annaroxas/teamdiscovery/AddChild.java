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

public class AddChild extends AppCompatActivity {
    private static final String TAG = AddChild.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            Log.d(TAG, "savedInstanceState DALE fucked right up");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_child);
        initImages();

    }


    private void initImages(){

        ImageView image_view = (ImageView) findViewById(R.id.imageView);
        Glide.with(this)
                .load(R.drawable.children)
                .fitCenter()
                .into(image_view);

        ImageView image_view_three = (ImageView) findViewById(R.id.imageView3);
        Glide.with(this)
                .load(R.drawable.guardian)
                .fitCenter()
                .into(image_view_three);

        ImageView image_view_two = (ImageView) findViewById(R.id.imageView2);
        Glide.with(this)
                .load(R.drawable.childid)
                .fitCenter()
                .into(image_view_two);

        ImageButton addavatar_button = (ImageButton) findViewById(R.id.anc_add_avatar);
        Glide.with(this)
                .load(R.drawable.addavatar)
                .fitCenter()
                .into(addavatar_button);

        ImageButton create_button = (ImageButton) findViewById(R.id.anc_bttn_create);
        Glide.with(this)
                .load(R.drawable.create)
                .fitCenter()
                .into(create_button);

        ImageButton setting_button = (ImageButton) findViewById(R.id.anc_sett);
        Glide.with(this)
                .load(R.drawable.settings)
                .fitCenter()
                .into(setting_button);

        ImageButton aboot_button = (ImageButton) findViewById(R.id.anc_info); //Eh?
        Glide.with(this)
                .load(R.drawable.about)
                .fitCenter()
                .into(aboot_button); //Eh?
    }
}
