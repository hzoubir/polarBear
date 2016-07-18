package com.example.hzoubir.haitamutilsapp;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hzoubir on 1/28/16.
 */
public class ProgressSpinnerTool {

    private static ProgressSpinnerTool tool;
    private static ProgressDialog dialog;
    private ProgressSpinnerTool(Context context){
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);

    }

    public  static synchronized ProgressSpinnerTool getProgressSpinnerTool(Context context){
        if (tool==null){
            tool=new ProgressSpinnerTool(context);
            return tool;
        }
        else
        {
            return tool;
        }

    }
    public void showSpinner(int i){
        dialog.show();
        if (i!=0){
            disableSpinner(i);
        }

    }
    public void disableSpinner(int i){
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        }, i);
    }


}
