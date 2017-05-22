package com.example.annaroxas.teamdiscovery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by annaroxas on 2017-05-04.
 */

public class LoginPage extends AppCompatActivity {
    private static final String TAG = LoginPage.class.getSimpleName();
    EditText passText, nameText;
    TextView textViewPass, textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Log.d(TAG, "savedInstanceState fucked right up");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        //Grab the reference of EditText fields
        passText = (EditText) findViewById(R.id.edit_text_password);
        nameText = (EditText) findViewById(R.id.edit_text_username);

        //Add text watcher to the EditText fields
        passText.addTextChangedListener(checkEditorText);
        nameText.addTextChangedListener(checkEditorText);

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