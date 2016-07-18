package com.example.hzoubir.haitamutilsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link BlankFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link BlankFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String WEBVIEWURL="https://www.google.com";
    private static final String TAG = BlankFragment.class.getSimpleName();


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private WebView webview;
    private RelativeLayout relativeLayout;
    private Button button;
   


//    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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
        // Inflate the layout for this fragment
        final Fragment fragment = this;
        View view= inflater.inflate(R.layout.fragment_blank, container, false);
        relativeLayout=(RelativeLayout) view.findViewById(R.id.noConnectionLayaout);
//        relativeLayout.setVisibility(RelativeLayout.GONE);
        webview = (WebView) view.findViewById(R.id.webView1);
        addListenertoButton(view);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!ProjectUtils.isConnected(getActivity())){
                    switchLoError();

                }
                else {
                    view.loadUrl(url);


                }
                return true;
            }
        });

//        webview.setWebViewClient(new WebChromeClient() {
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                Toast.makeText(fragment, description, Toast.LENGTH_SHORT).show();
//            }
//        });
        if (savedInstanceState== null) {

            webview.loadUrl(WEBVIEWURL);
        }
        else{
            webview.restoreState(savedInstanceState);

        }

        if (ProjectUtils.isConnected(getActivity())){
            relativeLayout.setVisibility(RelativeLayout.GONE);
        }
        else{
            webview.setVisibility(WebView.GONE);
        }


        Log.d(TAG, "onCreateView() loading webViewUrl");

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }
    public void backOverriden(){
        WebView mweb=  ((WebView) this.getView().findViewById(R.id.webView1));
        if (mweb.canGoBack()){
            mweb.goBack();
        }
        else{
            this.getActivity().onBackPressed();
        }
    }
    public boolean isWebviewBack(){
        WebView mweb=  ((WebView) this.getView().findViewById(R.id.webView1));
        if (mweb.canGoBack()){
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState )
    {
        super.onSaveInstanceState(outState);
        webview.saveState(outState);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState)

    {
        super.onActivityCreated(savedInstanceState);
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
    private void addListenertoButton(View view){
         button = (Button) view.findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ProjectUtils.isConnected(getActivity())) {
                    switchToWebview();
                }
            }
        });
    }
    private void switchToWebview(){
        webview.setVisibility(WebView.VISIBLE);
        relativeLayout.setVisibility(RelativeLayout.GONE);
        webview.loadUrl(WEBVIEWURL);
    }
    private void switchLoError(){
        webview.setVisibility(WebView.GONE);
        relativeLayout.setVisibility(RelativeLayout.VISIBLE);
    }
}
