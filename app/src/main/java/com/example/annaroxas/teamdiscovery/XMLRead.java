package com.example.annaroxas.teamdiscovery;

/**
 * Created by Kenit on 5/26/2017.
 */

import android.app.Application;
import android.content.Context;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

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
                        } else if (tagname.equalsIgnoreCase(passText)) {
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
}