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

public class ChooseGame extends AppCompatActivity {

    private static final String TAG = ChooseGame.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            Log.d(TAG, "savedInstanceState DALE fucked right up");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_game);
        initImages();

    }


    private void initImages(){

        ImageView child_avatar = (ImageView) findViewById(R.id.cg_iv_avatr);
        Glide.with(this)
                .load(R.drawable.boy)
                .into(child_avatar);

        ImageButton start_game_button = (ImageButton) findViewById(R.id.cg_start_game);
        Glide.with(this)
                .load(R.drawable.cg_start_game)
                .fitCenter()
                .into(start_game_button);

        ImageButton cglvlone_button = (ImageButton) findViewById(R.id.cg_level1);
        Glide.with(this)
                .load(R.drawable.cg_level1)
                .fitCenter()
                .into(cglvlone_button);

        ImageButton cglvltwo_button = (ImageButton) findViewById(R.id.cg_level2);
        Glide.with(this)
                .load(R.drawable.cg_level2)
                .fitCenter()
                .into(cglvltwo_button);

        ImageButton setting_button = (ImageButton) findViewById(R.id.ac_sett);
        Glide.with(this)
                .load(R.drawable.settings)
                .fitCenter()
                .into(setting_button);

        ImageButton aboot_button = (ImageButton) findViewById(R.id.ac_info); //Eh?
        Glide.with(this)
                .load(R.drawable.about)
                .fitCenter()
                .into(aboot_button); //Eh?
    }
}
