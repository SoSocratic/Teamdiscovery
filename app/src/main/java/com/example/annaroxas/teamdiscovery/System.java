package com.example.annaroxas.teamdiscovery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class System extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "Oooooopsss";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_linear);
        initImages();
    }

    private void initImages(){

        ImageView logo_view = (ImageView) findViewById(R.id.logo_view);
        Glide.with(this)
                .load(R.drawable.logo)
                .into(logo_view);

        ImageView title_view = (ImageView) findViewById(R.id.title_view);
        Glide.with(this)
                .load(R.drawable.llrp_name_whole)
                .into(title_view);

        ImageView llrp_view = (ImageView) findViewById(R.id.as_llrp);
        Glide.with(this)
                .load(R.drawable.llrp_name)
                .into(llrp_view);

        ImageView logo = (ImageView) findViewById(R.id.logo_view);
        Glide.with(this)
                .load(R.drawable.logo)
                .into(logo);

        ImageButton setting_button = (ImageButton) findViewById(R.id.settings_button);
        Glide.with(this)
                .load(R.drawable.settings)
                .fitCenter()
                .into(setting_button);

        ImageButton login_button = (ImageButton) findViewById(R.id.login_button);
        Glide.with(this)
                .load(R.drawable.login)
                .fitCenter()
                .into(login_button);

        ImageButton createuser_button = (ImageButton) findViewById(R.id.createuser_button);
        Glide.with(this)
                .load(R.drawable.createuser)
                .fitCenter()
                .into(createuser_button);

        ImageButton aboot_button = (ImageButton) findViewById(R.id.imageButton7); //Eh?
        Glide.with(this)
                .load(R.drawable.about)
                .fitCenter()
                .into(aboot_button); //Eh?
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

    public void displayTest(View view){
        Intent showTest = new Intent(this, ChildConfig.class);
        startActivity(showTest);
    }
}
