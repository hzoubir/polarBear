package com.example.hzoubir.haitamutilsapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hzoubir on 2/16/16.
 */
public  class AppSettings extends PreferenceFragment

{
    private ListPreference listPreference;
    private MainActivity mActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        addPreferencesFromResource(R.xml.settings);
        // setting the list preference dynamically
        listPreference = (ListPreference) findPreference(AppConfig.getAppConfig().getActionBarColorPreferenceName());
        listPreference.setEntries(AppConfig.getAppConfig().getColors());
        listPreference.setEntryValues(AppConfig.getAppConfig().getColorsCodes());

        listPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                // AppConfig.getAppConfig().setBackGroundColorOfElement(getActivity().getActionBar(), getActivity().getSharedPreferences(preference.getKey(),  getActivity().MODE_PRIVATE));
                String key = preference.getKey();
                //AppConfig.getAppConfig().setBackGroundColorOfElement(getActivity().findViewById(R.id.toolbar), key, getActivity());
                return true;
            }
        });
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                AppConfig.getAppConfig().setBackGroundColorOfElement(getActivity().findViewById(R.id.toolbar), (String) newValue, getActivity());
                return true;
            }
        });

    }

    public  void onDestroyView(){
        mActivity.setTitle(R.string.app_name);
        super.onDestroyView();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mActivity.setTitle(R.string.action_Sensors);
        return super.onCreateView(inflater,container,savedInstanceState);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
    }







}
