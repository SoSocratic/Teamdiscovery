package com.example.annaroxas.teamdiscovery;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Owner on 30/05/2017.
 */

public class SingleResponseDialog extends DialogFragment {
    //Create empty constructor
    public SingleResponseDialog(){

    }

    //Create custom constructor
    public static SingleResponseDialog newInstance(String title, String message, String button){

        SingleResponseDialog frag = new SingleResponseDialog();
        //Instantiate an argument bundle
        Bundle args = new Bundle();

        //Slot each var into the args bundle
        args.putString("title", title);
        args.putString("message", message);
        args.putString("button", button);

        //Apply the args bundle to the frag Fragment
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        //Apply the fragment's stored arguments to new strings
        String title = getArguments().getString("title");
        String message = getArguments().getString("message");
        String button = getArguments().getString("button");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Set title of the dialog
        builder.setTitle(title);
        //Set message of the dialog
        builder.setMessage(message);
        //Set the button!
        builder.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });

        return builder.create();
    }


}
