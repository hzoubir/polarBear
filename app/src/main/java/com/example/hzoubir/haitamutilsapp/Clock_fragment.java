package com.example.hzoubir.haitamutilsapp;

import android.os.Bundle;
import android.app.Fragment;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;


import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import come.example.hzoubir.haitamutilsapp.views.ClockView;

/**
 * Created by hzoubir on 7/11/16.
 */
public class Clock_fragment extends Fragment {

    LinearLayout layout;
    ClockView digit1;
    ClockView digit2;
    ClockView digit3;
    ClockView digit4;
    Thread t;
    Timer timer;
    private Spinner spinner;
    private static final String[]paths = {"x1", "x2", "x3","x4","x5"};



    public Clock_fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.digital_clock, container,false);
        layout=(LinearLayout)view.findViewById(R.id.digitalCLock);
        digit1=(come.example.hzoubir.haitamutilsapp.views.ClockView)layout.findViewById(R.id.digit_1);
        digit2=(come.example.hzoubir.haitamutilsapp.views.ClockView)layout.findViewById(R.id.digit_2);
        digit3=(come.example.hzoubir.haitamutilsapp.views.ClockView)layout.findViewById(R.id.digit_3);
        digit4=(come.example.hzoubir.haitamutilsapp.views.ClockView)layout.findViewById(R.id.digit_4);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(onItemSelectedListener);

        return view;



    }
    public void start(){
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runThread();

            }
        }, 0,4000);
    }
    public void showNumber(int i1, int i2, int i3, int i4){

        digit1.showDigit(i1);
        digit2.showDigit(i2);
        digit3.showDigit(i3);
        digit4.showDigit(i4);



    }
    private void runThread() {

        new Thread() {
            public void run() {

                        getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                update();
                            }
                        });


                }

        }.start();
    }
    public void update(){
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int min = Calendar.getInstance().get(Calendar.MINUTE);
        int hourDigit1 = hour / 10;
        int hourdigit2 = hour % 10;
        int minDigit1 = min / 10;
        int mindigit2 = min % 10;
        showNumber(hourDigit1, hourdigit2, minDigit1, mindigit2);
    }
    @Override
    public void onStart(){
        super.onStart();
        start();

    }
    private AdapterView.OnItemSelectedListener onItemSelectedListener= new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

            switch (position) {
                case 0:
                    layout.setLayoutParams(new LinearLayout.LayoutParams(getActivity().getResources().getDimensionPixelSize(R.dimen.total_width),getActivity().getResources().getDimensionPixelSize(R.dimen.total_height)));
                    break;
                case 1:
                    // Whatever you want to happen when the second item gets selected
                    layout.setLayoutParams(new LinearLayout.LayoutParams(getActivity().getResources().getDimensionPixelSize(R.dimen.total_width)*2,getActivity().getResources().getDimensionPixelSize(R.dimen.total_height)*2));

                    break;
                case 2:
                    // Whatever you want to happen when the thrid item gets selected

                    layout.setLayoutParams(new LinearLayout.LayoutParams(getActivity().getResources().getDimensionPixelSize(R.dimen.total_width)*3,getActivity().getResources().getDimensionPixelSize(R.dimen.total_height)*3));

                    break;
                case 3:
                    layout.setLayoutParams(new LinearLayout.LayoutParams(getActivity().getResources().getDimensionPixelSize(R.dimen.total_width)*4,getActivity().getResources().getDimensionPixelSize(R.dimen.total_height)*4));

                    break;
                case 4:
                    layout.setLayoutParams(new LinearLayout.LayoutParams(getActivity().getResources().getDimensionPixelSize(R.dimen.total_width)*5,getActivity().getResources().getDimensionPixelSize(R.dimen.total_height)*5));

                    break;

            }
        }
        @Override
        public  void onNothingSelected(AdapterView<?> parent){
            // do nothing
        }

    };


}
