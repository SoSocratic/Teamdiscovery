package com.example.annaroxas.teamdiscovery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class System extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_linear);
    }

    public void startActivity(View view){
        Intent createNewUser = new Intent(this, Create_user.class);
        startActivity(createNewUser);
    }
}
