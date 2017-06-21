package com.example.annaroxas.teamdiscovery;

/**
 * Created by Kenit on 5/26/2017.
 */

import android.app.Application;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XMLRead extends Application {


    public static String XMLAuthParse(android.content.res.XmlResourceParser resParser, String nameText, String passText) throws IOException, XmlPullParserException {

        Boolean passCorrect = false;
        Boolean userCorrect = false;
        String text = "";
        try {
            // Create ResourceParser for XML file
            XmlResourceParser xpp = resParser;

            // check state
            int eventType = xpp.getEventType();


            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(passText)) {
                            passCorrect = true;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("name")) {
                            // collect and test username
                            if (text.equals(nameText)) {
                                userCorrect = true;
                            }
                        }
                        break;

                    default:
                        break;
                }
                eventType = xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(userCorrect && passCorrect) {
            return "Success!";
        }
        return "failed";
    }


    public static List<String> XMLWordParse(android.content.res.XmlResourceParser resParser, int three, int four, int five) throws IOException, XmlPullParserException {

        List<String> wordList = new ArrayList<String>();
        List<String> bufferList = new ArrayList<String>();
        String tagTest = "";
        String tag = "";
        int length = 3;
        int wordCount = 0;

        //try to open the wordBank XML file
        try {
            // Create ResourceParser for XML file
            XmlResourceParser xpp = resParser;

            // check state
            int eventType = xpp.getEventType();


            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        switch(xpp.getName()){
                            case "three":
                                tag = "three";
                                break;
                            case "four":
                                tag = "four";
                                break;
                            case "five":
                                tag = "five";
                                break;
                            default:
                                break;
                    }
                        break;
                    case XmlPullParser.TEXT:
                        //add the word to the buffer list
                        bufferList.add(xpp.getText());
                        Log.v("after add", bufferList.toString());
                        break;
                    case XmlPullParser.END_TAG:
                        tagTest = xpp.getName();

                        //if we reach a letter count end tag shuffle the list for randomization
                        if(tagTest.equals(tag)) {
                            Collections.shuffle(bufferList);
                            Log.v("post shuffle", bufferList.toString());

                            //get the number of words for the current letter count
                            //and set the starting tag to test later
                            switch (length) {
                                case 3:
                                    wordCount = three;
                                    break;
                                case 4:
                                    wordCount = four;
                                    break;
                                case 5:
                                    wordCount = five;
                                    break;
                            }

                            //grab the number of words needed for the letter count from the randomized bufferList
                            for (int count = 0; count < wordCount; count++) {
                                wordList.add(bufferList.get(count));
                                Log.v("word list add", wordList.toString());

                            }
                            //increment length of words being grabbed
                            length++;

                            //empty the buffer of words of the previous length
                            bufferList.clear();
                        }
                        break;

                    default:
                        break;
                }

                eventType = xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return the 5 words for the game mode
        return wordList;
    }
}
