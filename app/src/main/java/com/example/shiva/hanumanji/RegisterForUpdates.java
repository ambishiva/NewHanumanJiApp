package com.example.shiva.hanumanji;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hanumanji.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import adapter.MoreAdapter;
import base.AppMainTabActivity;
import constant.Constant;
import data.MoreModel;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by DELL on 08-02-2016.
 */
public class RegisterForUpdates extends Activity  implements View.OnClickListener {




    private TextView textViewSkip;
    private LoginButton loginButton;
    private ImageView imageViewFaceBookLogin;
    private CallbackManager callbackManager;
    private EditText editTextEmailAddress;
    private TextView textViewSubmit;
    String SENDER_ID = "1049383092379";
    private GcmMethods gcmmethods;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    GoogleCloudMessaging gcm;
    String regid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.thirdsplash);
       // String fontPath = "fonts/Face Your Fe.ttf";

        callbackManager = CallbackManager.Factory.create();
        initilaiseViews();
        initialiseListeners();

    }


    private void initilaiseViews() {

        textViewSkip = (TextView)findViewById(R.id.textViewSkip);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        imageViewFaceBookLogin = (ImageView)findViewById(R.id.imageViewLoginFacebook);
        editTextEmailAddress = (EditText)findViewById(R.id.editTextEmailAddress);
        textViewSubmit = (TextView)findViewById(R.id.textViewSubmit);
        gcmmethods = new GcmMethods();





        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(RegisterForUpdates.this);
            registerInBackground();
        } else {
            Log.i("Gcm", "No valid Google Play Services APK found.");
        }
    }

    private void registerInBackground() {

        new android.os.AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                String msg = "";
                try {
//                    if (gcm == null) {
//                        gcm = GoogleCloudMessaging.getInstance(RegisterForUpdates.this);
//                    }
//                    regid = gcm.register(SENDER_ID);
//                    //PreferenceConnector.writeString(LoginForgotActivity.this, PreferenceConnector.TOKEN, regid);
//                    msg = "Device registered, registration ID=" + regid;
//                    Log.v("the device id async", "Async taask" + msg);
//                    // You should send the registration ID to your server over HTTP,
//                    // so it can use GCM/HTTP or CCS to send messages to your app.
//                    // The request to your server should be authenticated if your app
//                    // is using accounts.
//                    sendRegistrationIdToBackend();
//
//                    // For this demo: we don't need to send it because the device
//                    // will send upstream messages to a server that echo back the
//                    // message using the 'from' address in the message.
//
//                    // Persist the regID - no need to register again.
//                    gcmmethods.storeRegistrationId(RegisterForUpdates.this, regid);


                        InstanceID instanceID = InstanceID.getInstance(RegisterForUpdates.this);

                        String token = instanceID.getToken(SENDER_ID,
                                GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

                         Log.i("", "GCM Registration Token: " + token);


                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            private void sendRegistrationIdToBackend() {

            }


            @Override
            protected void onPostExecute(String msg) {
                Log.e("the device id async", "Async taask" + msg);
            }

            /**
             * Override this method to perform a computation on a background thread. The
             * specified parameters are the parameters passed to {@link #execute}
             * by the caller of this task.
             * <p/>
             * This method can call {@link #publishProgress} to publish updates
             * on the UI thread.
             *
             * @param params The parameters of the task.
             * @return A result, defined by the subclass of this task.
             * @see #onPreExecute()
             * @see #onPostExecute
             * @see #publishProgress
             */


        }.execute(null, null, null);


    }


    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(RegisterForUpdates.this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, RegisterForUpdates.this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i("gcm", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }


    private void initialiseListeners() {
        textViewSkip.setOnClickListener(this);
        imageViewFaceBookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.performClick();
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                Toast.makeText(RegisterForUpdates.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                Intent mainActivity = new Intent(RegisterForUpdates.this, AppMainTabActivity.class);
                startActivity(mainActivity);
                finish();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

                Toast.makeText(RegisterForUpdates.this, "Error during Login", Toast.LENGTH_SHORT).show();

            }
        });

        textViewSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // new RegisterEmail().execute();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.textViewSkip:
                Intent mainActivity  = new Intent(RegisterForUpdates.this, AppMainTabActivity.class);
                startActivity(mainActivity);
                finish();
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }


//    class RegisterEmail extends AsyncTask<Void,Void,Boolean> {
//
//        Dialog mDialog;
//        String result = null;
//        StringBuffer response;
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            mDialog = new Dialog(RegisterForUpdates.this,android.R.style.Theme_Translucent_NoTitleBar);
//            mDialog.setContentView(R.layout.loader);
//            mDialog.setCancelable(false);
//            mDialog.show();
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//
//            boolean getLinkStatus = true;
//            String USER_AGENT = "Mozilla/5.0";
//            try{
//
//
//                URL obj = new URL(Constant.get_link_url);
//                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//                //add reuqest header
//                con.setRequestMethod("POST");
//                con.setRequestProperty("Content-Type", "application/json");
//                con.setRequestProperty("User-Agent",USER_AGENT);
//                con.setRequestProperty("Accept","*/*");
//                con.setRequestProperty("Accept-Charset", "UTF-8");
//
//                String urlParameters = "";
//
//                // Send post request
//                con.setDoOutput(true);
//                con.setDoInput(true);
//                // DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//                // wr.writeBytes(urlParameters);
//                // wr.flush();
//                // wr.close();
//
//                int responseCode = con.getResponseCode();
//
//                System.out.println("Post parameters : " + urlParameters);
//                System.out.println("Response Code : " + responseCode);
////
//                InputStream is ;//= con.getInputStream();
//
//
//                int status = con.getResponseCode();
//
//                if(status >= 400)
//                    is = con.getErrorStream();
//                else
//                    is = con.getInputStream();
//
//                BufferedReader in = new BufferedReader(
//                        new InputStreamReader(con.getErrorStream()));
//                String inputLine;
//                response = new StringBuffer();
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//
//                is.close();
//
//
//
//                //print result
//                // System.out.println(response.toString());
//
//
//
//            }catch (Exception e){
//
//                getLinkStatus = false;
//            }
//
//            return getLinkStatus;
//        }
//
//
//        @Override
//        protected void onPostExecute(Boolean result) {
//            super.onPostExecute(result);
//            if(result){
//
//                mDialog.dismiss();
//
//                try{
//
//                    JSONObject jsonObjectResult = new JSONObject(response.toString());
//                    JSONArray jsonArrayData = jsonObjectResult.getJSONArray("data");
//
//                    for(int i = 0;i<jsonArrayData.length();i++){
//
//                        JSONObject jsonObjectData = jsonArrayData.getJSONObject(i);
//                        String id = jsonObjectData.getString("id");
//                        String links = jsonObjectData.getString("links");
//                        String linkImage = jsonObjectData.getString("link_image");
//
//                        MoreModel moreModel = new MoreModel(id,links,linkImage);
//                        moreModelArrayList.add(moreModel);
//
//                    }
//
//                    moreAdapter = new MoreAdapter(getActivity(),R.layout.more_item,moreModelArrayList);
//                    listViewMore.setAdapter(moreAdapter);
//
//
//                }catch (Exception e){
//
//                }
//
//            }else {
//
//                mDialog.dismiss();
//            }
//        }
//    }
}
