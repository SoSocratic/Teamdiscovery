package com.example.annaroxas.teamdiscovery;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.String;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by annaroxas on 2017-05-04.
 */

public class LoginPage extends AppCompatActivity {
    private static final String TAG = LoginPage.class.getSimpleName();
    EditText passText, nameText;
    ImageButton login_button;
    Button  create_user_button;
    private static final String message = "Please fill in the required fields and try again.",
            button_label = "Ok", title = "No User name or Password entered";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Log.d(TAG, "savedInstanceState DALE fucked right up");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initImages();
        //Grab the reference of EditText fields
        passText = (EditText) findViewById(R.id.edit_text_password);
        nameText = (EditText) findViewById(R.id.edit_text_username);

        login_button = (ImageButton) findViewById(R.id.loginpage_login_button);
        create_user_button = (Button) findViewById(R.id.create_new_user_button);

        // Capture button clicks
        login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(nameText.getText().toString().length() > 0 &&
                        passText.getText().toString().length() > 0){
                    //get the username
                    String userName = nameText.getText().toString();
                    //get the password
                    String pass = passText.getText().toString();
                    //test the user/pass with the auth file
                    String successTest = null;
                    try {
                        successTest = XMLRead.XMLAuthParse(getApplicationContext().getResources().getXml(R.xml.auth), userName, pass);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                    //if login is successful start up the loginSuccess page, otherwise do nothing
                    if(successTest == "Success!"){
                        //declare login button intent
                        Intent loginAuthTest = new Intent(getApplicationContext(), SuccessLogin.class);
                        startActivity(loginAuthTest);
                    }

                } else {
                    FragmentManager fm = getFragmentManager();
                    SingleResponseDialog alertdFragment = SingleResponseDialog.newInstance(title,
                            message, button_label);
                    // Show Alert DialogFragment
                    alertdFragment.show(fm, "Fields Empty Error");
                }

            }
        });

        // Capture button clicks
        create_user_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent createUser = new Intent(getApplicationContext(),Create_user.class);

                //Bring existing activity instance to the foreground if it exists or create a
                // new one if it does not exist
                createUser.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(createUser);
            }
        });


    }

    private void initImages(){

        ImageView image_view_eight = (ImageView) findViewById(R.id.imageView8);
        Glide.with(this)
                .load(R.drawable.newpword)
                .fitCenter()
                .into(image_view_eight);

        ImageView image_view_nine = (ImageView) findViewById(R.id.imageView9);
        Glide.with(this)
                .load(R.drawable.username2)
                .fitCenter()
                .into(image_view_nine);

        ImageButton logo = (ImageButton) findViewById(R.id.loginpage_login_button);
        Glide.with(this)
                .load(R.drawable.sm_login)
                .fitCenter()
                .into(logo);

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