package com.example.annaroxas.teamdiscovery;

/**
 * Created by Owner on 08/06/2017.
 */

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class PresentReward extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlayerStateChangeListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private YouTubePlayer player;
    private boolean fullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.you_tube_reward);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(YouTConf.YOUTUBE_API_KEY, this);
    }


    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {

        //Initialize the player
        this.player = player;

        //Initialize the listener to monitor changes in the video playback state
        player.setPlayerStateChangeListener(this);

        //Set player to fullscreen and disable the controls
        player.setFullscreen(!fullscreen);
        player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);

        //Force the app into landscape mode when fullscreen is active if not already in landscape
        player.setFullscreenControlFlags(YouTubePlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION);

        //When in landscape the video will be in fullscreen
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);

        if (!wasRestored) {
            // Plays https://www.youtube.com/watch?v=hNfDNORPU4Y    BORKFRICA!
            player.loadVideo("hNfDNORPU4Y"); // BORK BORK BORK BORK
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(YouTConf.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {
        //THIS NEEDS TO BE CHANGED! IT SHOULD NOT GO TO CREATE USER ACTIVITY UPON
        //COMPLETION
        Intent create_user = new Intent(getApplicationContext(),System.class);

        //Bring existing activity instance to the foreground if it exists or create a
        // new one if it does not exist
        create_user.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(create_user);
    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }
}