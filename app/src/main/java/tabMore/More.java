package tabMore;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.app.hanumanji.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import adapter.MoreAdapter;
import base.BaseFragment;
import constant.Constant;
import data.MoreModel;
import data.Video;


public class More extends BaseFragment implements View.OnClickListener{

    View view;
    private ArrayList<MoreModel>  moreModelArrayList = new ArrayList<>();
    private ListView listViewMore;
    MoreAdapter moreAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {


        view  = inflater.inflate(R.layout.more, container, false);
        initialiseViews();
        initialiseListeners();
        getLinks();
        return view;
    }

    private void getLinks() {


        new GetLinks().execute();


    }


    private void initialiseViews() {


        listViewMore = (ListView)view.findViewById(R.id.listViewMore);

    }

    private void initialiseListeners() {


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

           default:
               break;

        }
    }

    private void openWebView() {
        String url = "http://www.google.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }




    class GetLinks extends AsyncTask<Void,Void,Boolean>{

        Dialog mDialog;
        String result = null;
        StringBuffer response;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mDialog = new Dialog(getActivity(),android.R.style.Theme_Translucent_NoTitleBar);
            mDialog.setContentView(R.layout.loader);
            mDialog.setCancelable(false);
            mDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            boolean getLinkStatus = true;
            String USER_AGENT = "Mozilla/5.0";
            try{


                URL obj = new URL(Constant.get_link_url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //add reuqest header
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("User-Agent",USER_AGENT);
                con.setRequestProperty("Accept","*/*");
                con.setRequestProperty("Accept-Charset", "UTF-8");

                String urlParameters = "";

                // Send post request
                con.setDoOutput(true);
                con.setDoInput(true);
               // DataOutputStream wr = new DataOutputStream(con.getOutputStream());
               // wr.writeBytes(urlParameters);
               // wr.flush();
               // wr.close();

                int responseCode = con.getResponseCode();

                System.out.println("Post parameters : " + urlParameters);
                System.out.println("Response Code : " + responseCode);
//
                InputStream is ;//= con.getInputStream();


                int status = con.getResponseCode();

                if(status >= 400)
                    is = con.getErrorStream();
                else
                    is = con.getInputStream();

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getErrorStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                is.close();



                //print result
               // System.out.println(response.toString());



            }catch (Exception e){

                getLinkStatus = false;
            }

            return getLinkStatus;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if(result){

                mDialog.dismiss();

                try{

                    JSONObject jsonObjectResult = new JSONObject(response.toString());
                    JSONArray jsonArrayData = jsonObjectResult.getJSONArray("data");

                    for(int i = 0;i<jsonArrayData.length();i++){

                        JSONObject jsonObjectData = jsonArrayData.getJSONObject(i);
                        String id = jsonObjectData.getString("id");
                        String links = jsonObjectData.getString("links");
                        String linkImage = jsonObjectData.getString("link_image");

                        MoreModel moreModel = new MoreModel(id,links,linkImage);
                        moreModelArrayList.add(moreModel);

                    }

                    moreAdapter = new MoreAdapter(getActivity(),R.layout.more_item,moreModelArrayList);
                    listViewMore.setAdapter(moreAdapter);


                }catch (Exception e){

                }

            }else {

                mDialog.dismiss();
            }
        }
    }
}
