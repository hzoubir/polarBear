package com.example.hzoubir.haitamutilsapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;


/**
 * Created by hzoubir on 1/26/16.
 */
public class DialogSample extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.DialogTitle)
                .setMessage(R.string.dialogMessage)

                .setPositiveButton(R.string.DialogOk, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
