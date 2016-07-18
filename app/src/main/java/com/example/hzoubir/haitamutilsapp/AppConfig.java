package com.example.hzoubir.haitamutilsapp;

import java.util.LinkedHashMap;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import java.util.ArrayList;

/**
 * Created by hzoubir on 2/16/16.
 */
public class AppConfig {
    private static AppConfig appConfig;
    private static final String WHITE="WHITE";
    private static final String RED="RED";
    private static final String BLACK="BLACK";
    private static final String GREEN="GREEN";
    private static final String BLUE="BLUE";
    private static final String CYAN="CYAN";
    private static final String YELLOW="YELLOW";
    private static final String MAGENTA="MAGENTA";
    private static final String GRAY="GRAY";
//    private static final String REDCODE="#FF0000";
//    private static final String BLACKCODE="#000000";
//    private static final String GREENCODE="#00FF00";
//    private static final String BLUECODE="#0000FF";
//    private static final String ORANGECODE="#FFA500";
//    private static final String AZURCODE="#F0FFFF";
//    private static final String PURPLECODE="#800080";
    private static LinkedHashMap colors;
    private final static String actionBarColorPreferenceName="actionBarColor";
//        private final static CharSequence[] actionBarColorPreferenceName="ActionBar";

    private final static String Default="DEFAULT";









    private AppConfig(){
            colors=new LinkedHashMap<String,String>();
            colors.put(WHITE,Color.WHITE);
            colors.put(RED,Color.RED);
            colors.put(BLACK,Color.BLACK);
            colors.put(GREEN,Color.GREEN);
            colors.put(BLUE,Color.BLUE);
            colors.put(CYAN,Color.CYAN);
            colors.put(YELLOW,Color.YELLOW);
            colors.put(MAGENTA,Color.MAGENTA);
            colors.put(GRAY,Color.GRAY);





    }

    public  static synchronized AppConfig getAppConfig(){
        if (appConfig==null){
            appConfig=new AppConfig();
            return appConfig;
        }
        else
        {
            return appConfig;
        }

    }
    public void setBackGroundColorOfElement(View view,String color,Context context){
        switch (color){

            case WHITE:
                view.setBackgroundColor(context.getResources().getColor( R.color.BackGroundWHITE));
                    break;

            case BLACK:
                view.setBackgroundColor(context.getResources().getColor( R.color.BackGroundBLACK));
                break;
            case CYAN:
                view.setBackgroundColor(context.getResources().getColor( R.color.BackGroundCYAN));
                break;
            case BLUE:
                view.setBackgroundColor(context.getResources().getColor( R.color.BackGroundBLUE));
                break;
            case GREEN:
                view.setBackgroundColor(context.getResources().getColor( R.color.BackGroundGREEN));
                break;
            case RED:
                view.setBackgroundColor(context.getResources().getColor( R.color.BackGroundRED));
                break;
            case YELLOW:
                view.setBackgroundColor(context.getResources().getColor( R.color.BackGroundYELLOW));
                break;
            case MAGENTA:
                view.setBackgroundColor(context.getResources().getColor( R.color.BackGroundMAGENTA));
                break;
            case GRAY:
                view.setBackgroundColor(context.getResources().getColor( R.color.BackGroundGRAY));
                break;
            case Default:
                view.setBackgroundColor(context.getResources().getColor( R.color.colorPrimary));
                break;
            default :
                view.setBackgroundColor(context.getResources().getColor( R.color.BackGroundGRAY));
                break;





        }
//        view.setBackgroundColor((int) colors.get(color));
        view.invalidate();
    }
    public void setStyle(View view,int style){

    }
    public CharSequence[] getColors(){
        CharSequence[] values=(CharSequence[]) colors.keySet().toArray(new CharSequence[colors.size()]);
        return values;
    }
    public CharSequence[] getColorsCodes(){
        CharSequence[] values=(CharSequence[]) colors.keySet().toArray(new CharSequence[colors.size()]);
        return values;
    }
    public String getActionBarColorPreferenceName(){
        return actionBarColorPreferenceName;
    }
    public String getDefaulColor(){
        return Default;
    }


}
