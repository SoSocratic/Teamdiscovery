package com.example.annaroxas.teamdiscovery;


import android.media.Image;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Objects;


public class Create_user extends AppCompatActivity {

    private static final String TAG = LoginPage.class.getSimpleName();
    private static ImageButton button_confirm;
    EditText passText, nameText, confirmText, emailText;
    private static final String message = "Please check all fields to ensure " +
            "they are filled in and password fields match.",
            button_label = "Ok", title = "Information Entry Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        initImages();

        //Grab the reference of EditText fields
        passText = (EditText) findViewById(R.id.ac_tx_pword);
        nameText = (EditText) findViewById(R.id.ac_tx_uname);
        confirmText = (EditText) findViewById(R.id.ac_tx_repword);
        emailText = (EditText) findViewById(R.id.ac_tx_email);

        button_confirm = (ImageButton) findViewById(R.id.cu_create);

        // Capture button clicks
        button_confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(Objects.equals(passText.getText().toString(),confirmText.getText().toString()) &&
                        (nameText.getText().toString().length() > 0 &&
                        passText.getText().toString().length() > 0)){
            /*
        * CODE TO INSERT NEW USER INFO INTO XML
        * GOES HERE
        * */

                } else {
                    FragmentManager fm = getFragmentManager();
                    SingleResponseDialog alertdFragment = SingleResponseDialog.newInstance(title,
                            message, button_label);
                    // Show Alert DialogFragment
                    alertdFragment.show(fm, "Confirm Error");
                }

            }
        });
    }

    private void initImages(){

        ImageButton cucreate_button = (ImageButton) findViewById(R.id.cu_create);
        Glide.with(this)
                .load(R.drawable.cu_create)
                .fitCenter()
                .into(cucreate_button);

        ImageButton aboot_button = (ImageButton) findViewById(R.id.imageButton7); //Eh?
        Glide.with(this)
                .load(R.drawable.about)
                .fitCenter()
                .into(aboot_button); //Eh?

        ImageButton settings_button = (ImageButton) findViewById(R.id.settings_button);
        Glide.with(this)
                .load(R.drawable.settings)
                .fitCenter()
                .into(settings_button);

        ImageView image_view_four = (ImageView) findViewById(R.id.imageView4);
        Glide.with(this)
                .load(R.drawable.newpword)
                .fitCenter()
                .into(image_view_four);

        ImageView image_view_seven = (ImageView) findViewById(R.id.imageView7);
        Glide.with(this)
                .load(R.drawable.newpword)
                .fitCenter()
                .into(image_view_seven);

        ImageView image_view_six = (ImageView) findViewById(R.id.imageView6);
        Glide.with(this)
                .load(R.drawable.newemail)
                .fitCenter()
                .into(image_view_six);

        ImageView image_view_five = (ImageView) findViewById(R.id.imageView5);
        Glide.with(this)
                .load(R.drawable.username2)
                .fitCenter()
                .into(image_view_five);


    }

}
