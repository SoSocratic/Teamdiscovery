package com.example.annaroxas.teamdiscovery;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class TwoResponseDialog extends DialogFragment {
    //Create empty constructor
    public TwoResponseDialog(){

    }

    //Create custom constructor
    public static TwoResponseDialog newInstance(String title, String message, String ok,
                                                String cancel, int type){

        TwoResponseDialog frag = new TwoResponseDialog();
        //Instantiate an argument bundle
        Bundle args = new Bundle();

        //Slot each var into the args bundle
        args.putString("title", title);
        args.putString("message", message);
        args.putString("ok", ok);
        args.putString("cancel", cancel);
        args.putInt("type", type);

        //Apply the args bundle to the frag Fragment
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        //Apply the fragment's stored arguments to new strings
        String title = getArguments().getString("title");
        String message = getArguments().getString("message");
        String ok = getArguments().getString("ok");
        String cancel = getArguments().getString("cancel");
        final int type = getArguments().getInt("type");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Set title of the dialog
        builder.setTitle(title);
        //Set message of the dialog
        builder.setMessage(message);
        //Set the button!
        builder.setPositiveButton(ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                if(type == 0){
                    //EXECUTE SOME KIND OF CODE
                } else {
                    //EXECUTE SOME OTHER KIND OF CODE
                }
            }
        });
        builder.setNegativeButton(cancel, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });

        return builder.create();
    }

}
