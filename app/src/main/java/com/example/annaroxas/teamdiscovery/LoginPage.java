package com.example.annaroxas.teamdiscovery;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.lang.String;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by annaroxas on 2017-05-04.
 */

public class LoginPage extends AppCompatActivity {
    private static final String TAG = LoginPage.class.getSimpleName();
    EditText passText, nameText;
    Button login_button, create_user_button;
    private static final String message = "Please fill in the required fields and try again.",
            button_label = "Ok", title = "No User name or Password entered";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Log.d(TAG, "savedInstanceState DALE fucked right up");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        //Grab the reference of EditText fields
        passText = (EditText) findViewById(R.id.edit_text_password);
        nameText = (EditText) findViewById(R.id.edit_text_username);

        //Add text watcher to the EditText fields
        passText.addTextChangedListener(checkEditorText);
        nameText.addTextChangedListener(checkEditorText);
        login_button = (Button) findViewById(R.id.loginpage_login_button);
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
                Intent testgame = new Intent(getApplicationContext(),TestGame.class);

                //Bring existing activity instance to the foreground if it exists or create a
                // new one if it does not exist
                testgame.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(testgame);
            }
        });


    }

    private final TextWatcher checkEditorText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if( nameText.getText().toString().length() == 0 )
                nameText.setError( "Username is a required field" );

            if( passText.getText().toString().length() == 0 )
                passText.setError( "Password is a required field" );
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}