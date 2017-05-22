package com.example.annaroxas.teamdiscovery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by annaroxas on 2017-05-04.
 */

public class LoginPage extends AppCompatActivity {
    private static final String TAG = LoginPage.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState == null){
            Log.d (TAG, "savedInstanceState fucked right up");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }
}
