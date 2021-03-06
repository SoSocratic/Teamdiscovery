package com.example.annaroxas.teamdiscovery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ClipData;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;

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

    //single character tags for letter matching
    char option1Tag, option2Tag, option3Tag, option4Tag, option5Tag;
    char choice1Tag, choice2Tag, choice3Tag, choice4Tag, choice5Tag;

    //ints for holding the imageView id's for checking
    int option1ID, option2ID, option3ID, option4ID, option5ID;
    int choice1ID, choice2ID, choice3ID, choice4ID, choice5ID;


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
        option1ID = R.id.option_1;
        option2 = (ImageView)findViewById(R.id.option_2);
        option2ID = R.id.option_2;
        option3 = (ImageView)findViewById(R.id.option_3);
        option3ID = R.id.option_3;
        option4 = (ImageView)findViewById(R.id.option_4);
        option4ID = R.id.option_4;
        option5 = (ImageView)findViewById(R.id.option_5);
        option5ID = R.id.option_5;

        //view containers to drop onto
        choice1 = (ImageView)findViewById(R.id.choice_1);
        choice1ID = R.id.choice_1;
        choice2 = (ImageView)findViewById(R.id.choice_2);
        choice2ID = R.id.choice_2;
        choice3 = (ImageView)findViewById(R.id.choice_3);
        choice3ID = R.id.choice_3;
        choice4 = (ImageView)findViewById(R.id.choice_4);
        choice4ID = R.id.choice_4;
        choice5 = (ImageView)findViewById(R.id.choice_5);
        choice5ID = R.id.choice_5;


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
        List<Character> charList = new ArrayList<Character>();
        Context c = getApplicationContext();

        //change progress picture
        int id = c.getResources().getIdentifier("drawable/prg_"+ Integer.toString(currentRound) + "of5", null, packa);
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

        wordHintPic.setVisibility(View.VISIBLE);
        for (char cha : word.toCharArray()) {
            charList.add(cha);
        }



        //use the length to set the characters and make the used blocks visible
        switch(length){
            //the word has three letters
            case 3:
                //set character tags for choices for matching
                choice1Tag = charList.get(0);
                choice2Tag = charList.get(1);
                choice3Tag = charList.get(2);


                //set containers to drop letters into
                id = c.getResources().getIdentifier("drawable/bw_"+charList.get(0), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice1);
                choice1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList.get(1), null,packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice2);
                choice2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList.get(2), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice3);
                choice3.setVisibility(View.VISIBLE);

                //ensure uneeded choices are not rendered or used
                choice4.setVisibility(View.GONE);
                choice5.setVisibility(View.GONE);


                //shuffle charList for randomness
                Collections.shuffle(charList);

                //set the dragable letters tags
                option1Tag = charList.get(0);
                option2Tag = charList.get(1);
                option3Tag = charList.get(2);

                //set the images for each letters dragable
                id = c.getResources().getIdentifier("drawable/"+charList.get(0), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option1);
                option1.setVisibility(View.VISIBLE);

                id = c.getResources().getIdentifier("drawable/"+charList.get(1), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option2);
                option2.setVisibility(View.VISIBLE);

                id = c.getResources().getIdentifier("drawable/"+charList.get(2), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option3);
                option3.setVisibility(View.VISIBLE);

                //ensure unneeded options are not rendered or used
                option4.setVisibility(View.GONE);
                option5.setVisibility(View.GONE);

                break;
            //the word has four letters
            case 4:

                //set character tags for choices for matching
                choice1Tag = charList.get(0);
                choice2Tag = charList.get(1);
                choice3Tag = charList.get(2);
                choice4Tag = charList.get(3);


                //set containers to drop letters into
                id = c.getResources().getIdentifier("drawable/bw_"+charList.get(0), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice1);
                choice1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList.get(1), null,packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice2);
                choice2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList.get(2), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice3);
                choice3.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList.get(3), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice4);
                choice4.setVisibility(View.VISIBLE);

                //ensure uneeded choices are not rendered or used
                choice5.setVisibility(View.GONE);

                //shuffle charList for randomness
                Collections.shuffle(charList);

                //set the dragable letters tags
                option1Tag = charList.get(0);
                option2Tag = charList.get(1);
                option3Tag = charList.get(2);
                option4Tag = charList.get(3);

                //set the images for each letters dragable
                id = c.getResources().getIdentifier("drawable/"+charList.get(0), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option1);
                option1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList.get(1), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option2);
                option2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList.get(2), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option3);
                option3.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList.get(3), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option4);
                option4.setVisibility(View.VISIBLE);

                //ensure unneeded options are not rendered or used
                option5.setVisibility(View.GONE);


                break;

            //the word has five letters
            case 5:

                //set character tags for choices for matching
                choice1Tag = charList.get(0);
                choice2Tag = charList.get(1);
                choice3Tag = charList.get(2);
                choice4Tag = charList.get(3);
                choice5Tag = charList.get(4);


                //set containers to drop letters into
                id = c.getResources().getIdentifier("drawable/bw_"+charList.get(0), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice1);
                choice1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList.get(1), null,packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice2);
                choice2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList.get(2), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice3);
                choice3.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList.get(3), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice4);
                choice4.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList.get(4), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(choice5);
                choice5.setVisibility(View.VISIBLE);

                //shuffle charList for randomness
                Collections.shuffle(charList);

                //set the dragable letters tags
                option1Tag = charList.get(0);
                option2Tag = charList.get(1);
                option3Tag = charList.get(2);
                option4Tag = charList.get(3);
                option5Tag = charList.get(4);

                //set the images for each letters dragable
                id = c.getResources().getIdentifier("drawable/"+charList.get(0), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option1);
                option1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList.get(1), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option2);
                option2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList.get(2), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option3);
                option3.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList.get(3), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option4);
                option4.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList.get(4), null, packa);
                Glide.with(this)
                        .load(id)
                        .fitCenter()
                        .into(option5);
                option5.setVisibility(View.VISIBLE);


                break;

            default:
                break;
        }
    }

    //make a workaround for not being able to use tags because of Glide
    private char getDragTag(int id){

        char tag = '0';


        if(id == option1ID) {
            tag = option1Tag;
        }else if(id == option2ID){
            tag = option2Tag;
        }else if(id == option3ID){
            tag = option3Tag;
        }else if(id == option4ID){
            tag = option4Tag;
        }else if(id == option5ID){
            tag = option5Tag;
        }else if(id == choice1ID){
            tag = choice1Tag;
        }else if(id == choice2ID){
            tag = choice2Tag;
        }else if(id == choice3ID){
            tag = choice3Tag;
        }else if(id == choice4ID){
            tag = choice4Tag;
        }else if(id == choice5ID){
            tag = choice5Tag;
        }

        //return the character tag to identify which option matches which choice
        return tag;
    }

    private class ChoiceDragListener implements OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            //handle the dragged view being dropped over a drop view
            View view = (View) event.getLocalState();
            //view dragged item is being dropped on
            ImageView dropTarget = (ImageView) v;

            //tag for use in identification
            char dragTag = getDragTag(view.getId());
            char dropTag = getDragTag(dropTarget.getId());

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

                    // test if icon is in wrong spot
                    if(!(dragTag == dropTag)){
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

                        if(currentRound == 5){
                            //end activity and show the final reward and congrats for completing the whole round
                            //change hint picture to success pic
                            Context c = getApplicationContext();

                            //remove everything else
                            option1.setVisibility(View.GONE);
                            option2.setVisibility(View.GONE);
                            option3.setVisibility(View.GONE);
                            option4.setVisibility(View.GONE);
                            option5.setVisibility(View.GONE);

                            choice1.setVisibility(View.GONE);
                            choice2.setVisibility(View.GONE);
                            choice3.setVisibility(View.GONE);
                            choice4.setVisibility(View.GONE);
                            choice5.setVisibility(View.GONE);

                            progressPic.setVisibility(View.GONE);

                            //change hint picture
                            int id = c.getResources().getIdentifier("drawable/success", null, packa);
                            Glide.with(c)
                                    .load(id)
                                    .fitCenter()
                                    .into(wordHintPic);

                            //game has been won, start reward
                            wordHintPic.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View arg0) {
                                    Intent showRewards = new Intent(getApplicationContext(),PresentReward.class);

                                    //Bring existing activity instance to the foreground if it exists or create a
                                    // new one if it does not exist
                                    showRewards.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    startActivity(showRewards);
                                }
                            });

                            break;
                        }
                        Context c = getApplicationContext();
                        //increment the currentRound
                        currentRound++;

                        currentWord = wordList.get(0);
                        wordList.remove(0);
                        GameSetup(currentWord);
                        //increment the currentRound

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
