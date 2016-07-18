package come.example.hzoubir.haitamutilsapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.example.hzoubir.haitamutilsapp.R;
/**
 * Created by hzoubir on 7/11/16.
 */
public class ClockView extends LinearLayout {

    View top_top_view;
    View middle_view;
    View bottom_view;
    LinearLayout top_left;
    LinearLayout top_right;
    LinearLayout bottom_left;
    LinearLayout bottom_right;
    LinearLayout layout;



    public ClockView(Context context) {
        super(context);
//        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        inflater.inflate(R.layout.clock_layout, this, true);

        init();

    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();


    }

    private void init(){
        inflate(getContext(),R.layout.clock_layout,this);
        top_top_view= (View)findViewById(R.id.top_top_layout);
        middle_view= (View) findViewById(R.id.middle_layout);
        bottom_view= (View) findViewById(R.id.bottom_top_layout);
        top_left=(LinearLayout) findViewById(R.id.top_left_layout);
        top_right=(LinearLayout) findViewById(R.id.top_right_layout);
        bottom_left=(LinearLayout) findViewById(R.id.bottom_left_layout);
        bottom_right=(LinearLayout) findViewById(R.id.bottom_right_layout);
        layout =(LinearLayout) findViewById(R.id.clock_layout);
        showDigit(0);


    }
    public void showDigit(int i){
        top_top_view.setVisibility(View.VISIBLE);
        middle_view.setVisibility(View.VISIBLE);
        bottom_view.setVisibility(View.VISIBLE);
        top_left.setVisibility(View.VISIBLE);
        top_right.setVisibility(View.VISIBLE);
        bottom_left.setVisibility(View.VISIBLE);
        bottom_right.setVisibility(View.VISIBLE);
        switch (i) {

            case 0:
                middle_view.setVisibility(View.INVISIBLE);
                break;
            case 1:
                middle_view.setVisibility(View.INVISIBLE);
                top_left.setVisibility(View.INVISIBLE);
                bottom_left.setVisibility(View.INVISIBLE);
                top_top_view.setVisibility(View.INVISIBLE);
                bottom_view.setVisibility(View.INVISIBLE);
                break;
            case 2:
                top_left.setVisibility(View.INVISIBLE);
                bottom_right.setVisibility(View.INVISIBLE);
                break;
            case 3:
                top_left.setVisibility(View.INVISIBLE);
                bottom_left.setVisibility(View.INVISIBLE);
                break;
            case 4:
                top_top_view.setVisibility(View.INVISIBLE);
                bottom_view.setVisibility(View.INVISIBLE);
                bottom_left.setVisibility(View.INVISIBLE);
                break;
            case 5:
                bottom_left.setVisibility(View.INVISIBLE);
                top_right.setVisibility(View.INVISIBLE);
                break;
            case 6:
                top_right.setVisibility(View.INVISIBLE);
                break;
            case 7:
                top_left.setVisibility(View.INVISIBLE);
                bottom_left.setVisibility(View.INVISIBLE);
                bottom_view.setVisibility(View.INVISIBLE);
                middle_view.setVisibility(View.INVISIBLE);
                break;
            case 8:
                //all is good and fine
                break;
            case 9:
                bottom_left.setVisibility(View.INVISIBLE);
                break;








        }




    }


}
