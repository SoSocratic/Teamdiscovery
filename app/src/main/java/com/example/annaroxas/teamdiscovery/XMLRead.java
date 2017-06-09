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

public class XMLRead extends Application{


public static String XMLParse(android.content.res.XmlResourceParser resParser, String nameText, String passText) throws IOException, XmlPullParserException {
    // Create ResourceParser for XML file
    XmlResourceParser xpp = resParser;

    // check state
    int eventType = xpp.getEventType();
    Boolean passCorrect = false;
    Boolean userCorrect = false;

    while (eventType != XmlPullParser.END_DOCUMENT) {
        // instead of the following if/else if lines
        // you should custom parse your xml
        if(eventType == XmlPullParser.START_TAG) {
            //check content against entered values
            if(passText == xpp.getName()){
                Boolean passSuccess = true;
                return "pass correct";
            }
            else if("name" == xpp.getName()){
                Boolean onUser = true;
                return "on user";
            }

        } else if(eventType == XmlPullParser.TEXT) {
            if (nameText == xpp.getText()){
                userCorrect = true;
                return "Got user";
            }

        }
        if(userCorrect == true && passCorrect == true){
            xpp.close();
            return "Success!";
        }
        //move onto the next part in XML doc
        eventType = xpp.next();
    }


    // indicate app done reading the resource.
    xpp.close();
    return "fail";
    }
}