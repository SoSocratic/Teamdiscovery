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

    private ImageView option1, option2, option3, option4, option5, choice1, choice2, choice3, choice4, choice5, wordHintPic, progressPic;
    private int currentRound;
    private String currentWord;
    List<String> wordList;
    String packa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);
        initImages();

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

        //set tagIDs to allow for matching correct containers to letters
        option1.setTag(1);
        option2.setTag(2);
        option3.setTag(3);
        option4.setTag(4);
        option5.setTag(5);

        choice1.setTag(1);
        choice2.setTag(2);
        choice3.setTag(3);
        choice4.setTag(4);
        choice5.setTag(5);


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

        wordList = Collections.<String>emptyList();
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
                id = c.getResources().getIdentifier("drawable/"+charList[0], null, packa);
                option1.setImageResource(id);
                option1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[1], null, packa);
                option2.setImageResource(id);
                option2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[2], null, packa);
                option3.setImageResource(id);
                option3.setVisibility(View.VISIBLE);

                //ensure unneeded options are not rendered or used
                option4.setVisibility(View.GONE);
                option5.setVisibility(View.GONE);

                //set containers to drop letters into
                id = c.getResources().getIdentifier("drawable/bw_"+charList[0], null, packa);
                choice1.setImageResource(id);
                choice1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[1], null,packa);
                choice2.setImageResource(id);
                choice2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[2], null, packa);
                choice3.setImageResource(id);
                choice3.setVisibility(View.VISIBLE);

                //ensure uneeded choices are not rendered or used
                choice4.setVisibility(View.GONE);
                choice5.setVisibility(View.GONE);

                break;
            case 4:
                id = c.getResources().getIdentifier("drawable/"+charList[0], null, packa);
                option1.setImageResource(id);
                option1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[1], null, packa);
                option2.setImageResource(id);
                option2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[2], null, packa);
                option3.setImageResource(id);
                option3.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[3], null, packa);
                option4.setImageResource(id);
                option4.setVisibility(View.VISIBLE);

                //ensure unneeded options are not rendered or used
                option5.setVisibility(View.GONE);

                //set containers to drop letters into
                id = c.getResources().getIdentifier("drawable/bw_"+charList[0], null, packa);
                choice1.setImageResource(id);
                choice1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[1], null,packa);
                choice2.setImageResource(id);
                choice2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[2], null, packa);
                choice3.setImageResource(id);
                choice3.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[3], null, packa);
                choice4.setImageResource(id);
                choice4.setVisibility(View.VISIBLE);

                //ensure uneeded choices are not rendered or used
                choice5.setVisibility(View.GONE);

                break;
            case 5:
                id = c.getResources().getIdentifier("drawable/"+charList[0], null, packa);
                option1.setImageResource(id);
                option1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[1], null, packa);
                option2.setImageResource(id);
                option2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[2], null, packa);
                option3.setImageResource(id);
                option3.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[3], null, packa);
                option4.setImageResource(id);
                option4.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/"+charList[4], null, packa);
                option5.setImageResource(id);
                option5.setVisibility(View.VISIBLE);

                //set containers to drop letters into
                id = c.getResources().getIdentifier("drawable/bw_"+charList[0], null, packa);
                choice1.setImageResource(id);
                choice1.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[1], null,packa);
                choice2.setImageResource(id);
                choice2.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[2], null, packa);
                choice3.setImageResource(id);
                choice3.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[3], null, packa);
                choice4.setImageResource(id);
                choice4.setVisibility(View.VISIBLE);
                id = c.getResources().getIdentifier("drawable/bw_"+charList[4], null, packa);
                choice5.setImageResource(id);
                choice5.setVisibility(View.VISIBLE);

                break;

            default:
                break;
        }
    }

    private void initImages(){

        ImageView image_view = (ImageView) findViewById(R.id.imageView);
        Glide.with(this)
                .load(R.drawable.children)
                .fitCenter()
                .into(image_view);

        ImageView image_view_three = (ImageView) findViewById(R.id.imageView3);
        Glide.with(this)
                .load(R.drawable.guardian)
                .fitCenter()
                .into(image_view_three);

        ImageView image_view_two = (ImageView) findViewById(R.id.imageView2);
        Glide.with(this)
                .load(R.drawable.childid)
                .fitCenter()
                .into(image_view_two);

        ImageButton addavatar_button = (ImageButton) findViewById(R.id.anc_add_avatar);
        Glide.with(this)
                .load(R.drawable.addavatar)
                .fitCenter()
                .into(addavatar_button);

        ImageButton create_button = (ImageButton) findViewById(R.id.anc_bttn_create);
        Glide.with(this)
                .load(R.drawable.create)
                .fitCenter()
                .into(create_button);

        ImageButton setting_button = (ImageButton) findViewById(R.id.anc_sett);
        Glide.with(this)
                .load(R.drawable.settings)
                .fitCenter()
                .into(setting_button);

        ImageButton aboot_button = (ImageButton) findViewById(R.id.anc_info); //Eh?
        Glide.with(this)
                .load(R.drawable.about)
                .fitCenter()
                .into(aboot_button); //Eh?
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
                            wordHintPic.setImageResource(id);
                            break;
                        }
                        Context c = getApplicationContext();

                        //increment the currentRound
                        currentRound++;

                        //change progress picture
                        int id = c.getResources().getIdentifier("drawable/prg_"+ currentRound + "of5", null, packa);
                        progressPic.setImageResource(id);


                        //change hint picture
                        id = c.getResources().getIdentifier("drawable/"+ currentWord, null, packa);
                        wordHintPic.setImageResource(id);


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
