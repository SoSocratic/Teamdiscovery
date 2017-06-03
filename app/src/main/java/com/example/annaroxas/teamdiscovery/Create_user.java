package com.example.annaroxas.teamdiscovery;


import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;


public class Create_user extends AppCompatActivity {

    private static final String TAG = LoginPage.class.getSimpleName();
    private static Button button_confirm;
    EditText passText, nameText, confirmText, emailText;
    private static final String message = "Please re-enter your  and try again.",
            button_label = "Ok", title = "Passwords Mismatch.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        //Grab the reference of EditText fields
        passText = (EditText) findViewById(R.id.edit_text_pass);
        nameText = (EditText) findViewById(R.id.edit_text_user);
        confirmText = (EditText) findViewById(R.id.edit_text_confirm);
        emailText = (EditText) findViewById(R.id.edit_text_email);

        //Add text watcher to the EditText fields
        passText.addTextChangedListener(checkEditorText);
        nameText.addTextChangedListener(checkEditorText);
        confirmText.addTextChangedListener(checkEditorText);
        emailText.addTextChangedListener(checkEditorText);
        button_confirm = (Button) findViewById(R.id.confirm_button);

        // Capture button clicks
        button_confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(Objects.equals(passText.getText().toString(),confirmText.getText().toString())){
            /*
        * CODE TO INSERT NEW USER INFO INTO XML
        * GOES HERE
        * */

                } else {
                    FragmentManager fm = getFragmentManager();
                    SingleResponseDialog alertdFragment = SingleResponseDialog.newInstance(title,
                            "message", "Ok");
                    // Show Alert DialogFragment
                    alertdFragment.show(fm, "Confirm Error");
                }

            }
        });
    }


    private final TextWatcher checkEditorText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if( nameText.getText().toString().length() == 0 ){
                nameText.setError( "Username is a required field" );
            }

            if( passText.getText().toString().length() == 0 )
                passText.setError( "Password is a required field" );

            if( emailText.getText().toString().length() == 0 )
                emailText.setError( "Password is a required field" );

            if( confirmText.getText().toString().length() == 0 )
                confirmText.setError( "Password is a required field" );

        }
         @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
