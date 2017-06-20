package com.example.annaroxas.teamdiscovery;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.graphics.Typeface;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TestGame extends AppCompatActivity {

    private ImageView option1, option2, option3, option4, option5, choice1, choice2, choice3,
            choice4, choice5, wordHintPic, progressPic;
    private int currentRound;
    private String currentWord;
    List<String> wordList;
    String packa;

    //single haracter tags for letter matching
    char option1Tag, option2Tag, option3Tag, option4Tag, option5Tag;
    char choice1Tag, choice2Tag, choice3Tag, choice4Tag, choice5Tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);

        //set a string with the package for use later
        packa = getPackageName();

        //word hint picture
        wordHintPic = (ImageView)findViewById(R.id.sg_picture);
        //progress image and text
        progressPic = (ImageView)findViewById(R.id.sg_prg1);

        //views to drag and drop
        option1 = (ImageView)findViewById(R.id.option_1);
        option2 = (ImageView)findViewById(R.id.option_2);
        option3 = (ImageView)findViewById(R.id.option_3);
        option4 = (ImageView)findViewById(R.id.option_4);
        option5 = (ImageView)findViewById(R.id.option_5);

        //view containers to drop onto
        choice1 = (ImageView)findViewById(R.id.choice_1);
        choice2 = (ImageView)findViewById(R.id.choice_2);
        choice3 = (ImageView)findViewById(R.id.choice_3);
        choice4 = (ImageView)findViewById(R.id.choice_4);
        choice5 = (ImageView)findViewById(R.id.choice_5);


        //set touch listeners
        option1.setOnTouchListener(new ChoiceTouchListener());
        option2.setOnTouchListener(new ChoiceTouchListener());
        option3.setOnTouchListener(new ChoiceTouchListener());
        option4.setOnTouchListener(new ChoiceTouchListener());
        option5.setOnTouchListener(new ChoiceTouchListener());

        //set drag listeners
        choice1.setOnDragListener(new ChoiceDragListener());
        choice2.setOnDragListener(new ChoiceDragListener());
        choice3.setOnDragListener(new ChoiceDragListener());
        choice4.setOnDragListener(new ChoiceDragListener());
        choice5.setOnDragListener(new ChoiceDragListener());

        currentRound = 1;

        wordList = new ArrayList<String>();
        try {
            wordList = XMLRead.XMLWordParse(getApplicationContext().getResources().getXml(R.xml.game_bank), 2, 2, 1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        //setup first word and array of characters
        currentWord = wordList.get(0);

        wordList.remove(0);
        GameSetup(currentWord);
    }


    private final class ChoiceTouchListener implements OnTouchListener {

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //setup drag
                ClipData data = ClipData.newPlainText("", "");
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);

                return true;
            }
            else {
                return false;
            }
        }

    }

    private void GameSetup(String word) {
        int length = word.length();
        char[] charList = new char[length];
        charList = word.toCharArray();


        int id;
        Context c = getApplicationContext();

        //use the length to set the characters and make the used blocks visible
        switch(length){
            case 3:
                //set character tags for choices for matching
                choice1Tag = charList[0];
                choice2Tag = charList[1];
                choice3Tag = charList[2];

                //shuffle charList for randomness
                //Collections.shuffle(charList);

                id = c.getResources().getIdentifier("drawable/"+charList[0], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option1);
                option1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[1], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option2);
                option2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[2], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option3);
                option3.setVisibility(View.VISIBLE);

                //ensure unneeded options are not rendered or used
                option4.setVisibility(View.GONE);
                option5.setVisibility(View.GONE);

                //set containers to drop letters into
                id = c.getResources().getIdentifier("drawable/bw_"+charList[0], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice1);
                choice1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[1], null,packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice2);
                choice2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[2], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice3);
                choice3.setVisibility(View.VISIBLE);

                //ensure uneeded choices are not rendered or used
                choice4.setVisibility(View.GONE);
                choice5.setVisibility(View.GONE);

                break;
            case 4:
                id = c.getResources().getIdentifier("drawable/"+charList[0], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option1);
                option1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[1], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option2);
                option2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[2], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option3);
                option3.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[3], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option4);
                option4.setVisibility(View.VISIBLE);

                //ensure unneeded options are not rendered or used
                option5.setVisibility(View.GONE);

                //set containers to drop letters into
                id = c.getResources().getIdentifier("drawable/bw_"+charList[0], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice1);
                choice1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[1], null,packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice2);
                choice2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[2], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice3);
                choice3.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[3], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice4);
                choice4.setVisibility(View.VISIBLE);

                //ensure uneeded choices are not rendered or used
                choice5.setVisibility(View.GONE);

                break;
            case 5:
                id = c.getResources().getIdentifier("drawable/"+charList[0], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option1);
                option1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[1], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option2);
                option2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[2], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option3);
                option3.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[3], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option4);
                option4.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[4], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option5);
                option5.setVisibility(View.VISIBLE);

                //set containers to drop letters into
                id = c.getResources().getIdentifier("drawable/bw_"+charList[0], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice1);
                choice1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[1], null,packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice2);
                choice2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[2], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice3);
                choice3.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[3], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice4);
                choice4.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[4], null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice5);
                choice5.setVisibility(View.VISIBLE);

                break;

            default:
                break;
        }
    }

    private class ChoiceDragListener implements OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle drag events
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();
                    //view dragged item is being dropped on
                    ImageView dropTarget = (ImageView) v;

                    // test if icon is in wrong spot
                    if(view.getTag() != dropTarget.getTag()){
                        break;
                    }
                    //stop displaying the view where it was before it was dragged
                    view.setVisibility(View.INVISIBLE);

                    //view being dragged and dropped
                    ImageView dropped = (ImageView) view;
                    //update the image in the target view to reflect the data being dropped
                    dropTarget.setImageDrawable(dropped.getDrawable());


                    //check if all 3 are correct/invisible
                    if(option1.getVisibility() == view.INVISIBLE && option2.getVisibility() == view.INVISIBLE && option3.getVisibility() == view.INVISIBLE){
                        //win condition logic goes here
                        currentWord = wordList.get(0);
                        wordList.remove(0);
                        GameSetup(currentWord);
                        if(currentRound == 5){
                            //end activity and show the final reward and congrats for completing the whole round
                            //change hint picture to success pic
                            Context c = getApplicationContext();
                            //change hint picture
                            int id = c.getResources().getIdentifier("drawable/success", null, packa);
                            Glide.with(c)
                                    .load(id)
                                    .fitCenter()
                                    .into(wordHintPic);
                            break;
                        }
                        Context c = getApplicationContext();

                        //increment the currentRound
                        currentRound++;

                        //change progress picture
                        int id = c.getResources().getIdentifier("drawable/prg_"+ currentRound + "of5", null, packa);
                        Glide.with(c)
                                .load(id)
                                .fitCenter()
                                .into(progressPic);


                        //change hint picture
                        id = c.getResources().getIdentifier("drawable/"+ currentWord, null, packa);
                        Glide.with(c)
                                .load(id)
                                .fitCenter()
                                .into(wordHintPic);


                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}
