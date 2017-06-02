package com.example.annaroxas.teamdiscovery;

/**
 * Created by Kenit on 5/26/2017.
 */

import android.content.Context;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class XMLRead{

public String XMLParse(Context context) throws IOException, XmlPullParserException {
    // Create ResourceParser for XML file
    XmlResourceParser xpp = context.getResources().getXml(R.xml.auth);

    // check state
    int eventType = xpp.getEventType();
    String content = "test123";
    String str = "admin";
    Boolean passCorrect = false;
    Boolean userCorrect = false;

    while (eventType != XmlPullParser.END_DOCUMENT) {
        // instead of the following if/else if lines
        // you should custom parse your xml
        if(eventType == XmlPullParser.START_TAG) {
            //check content against entered values
            if(content == xpp.getName()){
                Boolean passSuccess = true;
            }
            else if("name" == xpp.getName()){
                Boolean onUser = true;
            }

            //System.out.println("Start tag "+xpp.getName());
            content = xpp.getName();
        } else if(eventType == XmlPullParser.TEXT) {
            str = (xpp.getText());
            userCorrect = true;
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