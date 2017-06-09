package com.example.annaroxas.teamdiscovery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        Intent logInUser = new Intent(this, LoginPage.class);
        startActivity(logInUser);
    }

    public void logInSuccess(View view){
        Intent loggedIn = new Intent(this, SuccessLogin.class);
        startActivity(loggedIn);
    }

    public void displayReward(View view){
        Intent showReward = new Intent(this, PresentReward.class);
        startActivity(showReward);
    }
}
