package com.example.hzoubir.haitamutilsapp;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import android.util.Log;
import android.widget.TextView;
import com.google.android.gms.location.LocationAvailability;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoogleMapsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoogleMapsFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = GoogleMapsFragment.class.getSimpleName();



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GoogleApiClient mGoogleApiClient;
    private Context mContext;
    private Location mLastLocation;
    private Boolean locAvailabe=false;
    TextView latitute;
    TextView longitude;


    public GoogleMapsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoogleMapsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoogleMapsFragment newInstance(String param1, String param2) {
        GoogleMapsFragment fragment = new GoogleMapsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_google_maps, container, false);
        latitute=(TextView) view.findViewById(R.id.textView);
        longitude=(TextView) view.findViewById(R.id.textView2);
        return view;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    @Override
    public void onConnected(Bundle connectionHint) {
        locAvailabe=true;
        if (isPermissionGranted()) {
            try {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
            }
            catch (SecurityException notGranted) {
                Log.d(TAG, "Error in retrieveing location");

            }
        }
        if (mLastLocation != null) {
            latitute.setText((String)("Latitude: "+String.valueOf(mLastLocation.getLatitude())));
            longitude.setText((String) ("Longitude: " + String.valueOf(mLastLocation.getLongitude())));
        }
    }


    public void onConnectionSuspended(int i){

    }
    public void onConnectionFailed(ConnectionResult conn){}
    public boolean isPermissionGranted(){
        PackageManager pm = mContext.getPackageManager();
        int hasPerm = pm.checkPermission(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                mContext.getPackageName());
        if (hasPerm != PackageManager.PERMISSION_GRANTED) {

                return false;
            // do stuff
        }
        else {
            Log.d(TAG, "retrieved connection");

            return true;
        }

    }
    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }
    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        locAvailabe=false;
        super.onStop();
    }
    public void updateLocation(){

        Log.d(TAG, "Update clicked");
        if (locAvailabe) {
            if (isPermissionGranted()) {
                try {


                    mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                            mGoogleApiClient);
//                    LocationManager service = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);

                    if(LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient)==null)
                     {
                        DialogSample DialogS = new DialogSample();

                        DialogS.show(getActivity().getFragmentManager(), "DIALOG");
                    }

                } catch (SecurityException notGranted) {
                    Log.d(TAG, "Error in retrieveing location");
                }
            }
            if (mLastLocation != null) {
                latitute.setText((String) ("Latitude: " + String.valueOf(mLastLocation.getLatitude())));
                longitude.setText((String) ("Longitude: " + String.valueOf(mLastLocation.getLongitude())));
            }
            else{
                latitute.setText((String) ("Latitude: " +"unavailable" ));
                longitude.setText((String) ("Longitude: " +"unavailable" ));
            }
        }
        else{
            Log.d(TAG, "location not available");
        }
    }

}
