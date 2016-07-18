package com.example.hzoubir.haitamutilsapp;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.FragmentManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.GravityCompat;

import java.util.prefs.Preferences;


public class MainActivity extends AppCompatActivity {
   private BlankFragment frag=new BlankFragment();
   private GoogleMapsFragment mapsfrag=new GoogleMapsFragment();
   private AppSettings setting=new AppSettings();
   private Clock_fragment clockFragment= new Clock_fragment();
    private static final String  FRAGTAG="FRAG";
    private static final String MAPSFRAG="MAPSFRAG";
   private ProgressSpinnerTool spin;
    private int SpinnerDuration=1000;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mTitle="title";
    private String mDrawerTitle="DrawerTitle";
    private NavigationView navigationMenu;
    private RelativeLayout header_layout;
    private static final String TAG = MainActivity.class.getSimpleName();
    private NavigationView.OnNavigationItemSelectedListener onItemselectedFromNavView;
    private final static int FRAG_MAP=2;
    private final static int FRAG_WEBVIEW=1;
    private final static int FRAG_CLOCK=3;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spin=ProgressSpinnerTool.getProgressSpinnerTool(this);
        //this.setTitle(R.string.action_Sensors);
        setContentView(R.layout.activity_main);
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        AppConfig.getAppConfig().setBackGroundColorOfElement(toolbar, preferences.getString( AppConfig.getAppConfig().getActionBarColorPreferenceName(),AppConfig.getAppConfig().getDefaulColor()), this);

        toolbar.setNavigationIcon(R.drawable.menu_image);

        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //toolbar.setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
               // toolbar.setTitle(mDrawerTitle);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        toolbar.setNavigationOnClickListener(mDrawerToggle.getToolbarNavigationClickListener());

//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
        if (savedInstanceState != null) {
            return;
        }
        else{
            BlankFragment frag=new BlankFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, frag).commit();

        }
        setSupportActionBar(toolbar);
        navigationMenu=(NavigationView) findViewById(R.id.nvView);
        header_layout=(RelativeLayout)navigationMenu.getHeaderView(0);
        header_layout.post(new Runnable() {
            @Override
            public void run() {
                header_layout.setBackground((Drawable) new BitmapDrawable(getResources(), ProjectUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.background_image, header_layout.getMeasuredWidth(), header_layout.getMeasuredHeight())));
                Log.d(TAG, "onCreateView() header_layout_with" + header_layout.getMeasuredWidth());

            }
        });
        setNavigationView();// setting the navigation menu listeners;


//        header_layout.setBackground((Drawable) new BitmapDrawable(getResources(),ProjectUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.background_image, header_layout.getMeasuredWidth(),  header_layout.getMeasuredWidth())));

//        navigationMenu.setNavigationItemSelectedListener();
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (id == R.id.action_WebView) {
            //BlankFragment frag=new BlankFragment();
            spin.showSpinner(SpinnerDuration);
            transaction.replace(R.id.fragment_container, frag);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        if (id == R.id.action_maps){
           // GoogleMapsFragment frag=new GoogleMapsFragment();
            spin.showSpinner(SpinnerDuration);
            transaction.replace(R.id.fragment_container, mapsfrag);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        if (id == R.id.show_clock){
            spin.showSpinner(SpinnerDuration);
//            getFragmentManager().beginTransaction().replace(R.id.fragment_container,  setting).addToBackStack(null).commit();
            transaction.replace(R.id.fragment_container, clockFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override public void onBackPressed(){
        if(closeDrawer()){
        }
        else {
            Fragment find = getFragmentManager().findFragmentById(R.id.fragment_container);
            if (find instanceof BlankFragment) {
                if (((BlankFragment) find).isWebviewBack())
                    ((BlankFragment) find).backOverriden();
                else if(getFragmentManager().getBackStackEntryCount() > 0)
                    getFragmentManager().popBackStack();

            } else if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
            } else{
                super.onBackPressed();
            }
        }
    }
    public void updateLocation(View v){
        Fragment find=getFragmentManager().findFragmentById(R.id.fragment_container);
        if (find instanceof GoogleMapsFragment){
            ((GoogleMapsFragment) find).updateLocation();

        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    public void setNavigationView(){
        navigationMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                return selectItem(menuItem);
            }

        });

        }
    public boolean selectItem(MenuItem menuItem){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        switch (menuItem.getItemId()){
            case R.id.webView_nav:
                closeDrawer();
                return loadFragById(transaction,FRAG_MAP);
            case R.id.location_nav:
                closeDrawer();
                return loadFragById(transaction,FRAG_WEBVIEW);
            case R.id.show_clock:
                closeDrawer();
                return loadFragById(transaction,FRAG_CLOCK);
            default:
                closeDrawer();
                return false;





        }
    }

    public boolean loadFragById(FragmentTransaction transaction,int id){

       switch (id){
           case FRAG_WEBVIEW:
               loadFrag(transaction,mapsfrag);
               return true;
           case FRAG_MAP:
               loadFrag(transaction,frag);
               return true;
           case FRAG_CLOCK:
               loadFrag(transaction,clockFragment);
                return true;
           default:
               return false;
       }

    }
    public void loadFrag(FragmentTransaction transaction, Fragment fragment){
        spin.showSpinner(SpinnerDuration);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    public boolean closeDrawer(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }
//    public static void setCustomTitle(int id){
//    }


}
