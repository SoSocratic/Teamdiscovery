package com.example.annaroxas.teamdiscovery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class System extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "Suck a dick dumbshit";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_linear);
    }

    public void createUser(View view){
        Intent createNewUser = new Intent(this, Create_user.class);
        startActivity(createNewUser);
    }

    public void startLogin(View view) {
        /**
         Intent intent = new Intent(this, DisplayMessageActivity.class);
         EditText editText = (EditText) findViewById(R.id.editText);
         String message = editText.getText().toString();
         intent.putExtra(EXTRA_MESSAGE, message);
         startActivity(intent);
         **/
        Intent logInUser = new Intent(this, LoginPage.class);
        startActivity(logInUser);
    }
}
