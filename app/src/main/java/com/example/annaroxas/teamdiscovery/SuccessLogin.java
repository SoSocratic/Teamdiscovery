package com.example.annaroxas.teamdiscovery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Owner on 22/05/2017.
 */

public class SuccessLogin extends AppCompatActivity {
    private static final String TAG = SuccessLogin.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState == null){
            Log.d (TAG, "savedInstanceState fucked right up");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success_activity);
    }
}