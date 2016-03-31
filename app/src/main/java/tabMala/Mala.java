package tabMala;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import  android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.app.hanumanji.R;
import java.util.ArrayList;
import java.util.List;

import adapter.PearlsAdapter;
import base.AppConstants;
import base.AppMainTabActivity;
import base.BaseFragment;
import data.PearlsData;



public class Mala extends BaseFragment {

    private ImageView imageViewReplay;
    View view;
    private ListView listViewPearls;
    private ArrayList<PearlsData> arrayListPearlsData;
    private PearlsAdapter pearlsAdapter;
    private TextView textViewBeadCountNumber;
    private TextView textViewMalaCountNumber;
    private int beadCountNumber;
    private int malaCountNumber;
    private LinearLayout relativeLayoutfirstPearl;
    private LinearLayout relativeLayoutsecondPearl;
    private ImageView imageViewInformation;
    int malaCount;
    int beadCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.mala, container, false);
        initialiseViews();
        initialiseListeners();
        return view;
    }


    private void initialiseViews() {

        imageViewReplay = (ImageView)view.findViewById(R.id.imageViewReplay);
        listViewPearls = (ListView)view.findViewById(R.id.listView);
        textViewBeadCountNumber = (TextView)view.findViewById(R.id.textViewBeadCountNumber);
        textViewMalaCountNumber = (TextView)view.findViewById(R.id.textViewMalaCountNumber);
        imageViewInformation = (ImageView)view.findViewById(R.id.imageViewInformation);

        final Animation slide_down = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.slide_down);

        final Animation slide_up = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.slide_up);

        final Animation slide_top_bottom = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.slide_out_up);

        final Animation zoomout = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.zoomout);


        relativeLayoutfirstPearl = (LinearLayout)view.findViewById(R.id.relativeLayoutFirstPearl);
        relativeLayoutsecondPearl = (LinearLayout)view.findViewById(R.id.relativeLayoutSecondPearl);
        relativeLayoutfirstPearl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relativeLayoutfirstPearl.startAnimation(slide_down);
                relativeLayoutfirstPearl.setVisibility(View.GONE);
                relativeLayoutsecondPearl.setVisibility(View.VISIBLE);
                relativeLayoutsecondPearl.startAnimation(slide_top_bottom);
                beadCount ++;
//                mala.beadCount(beadCount);
                if(beadCount==108){

                    malaCount++;
                 //   mala.malaCount(malaCount);
                    textViewMalaCountNumber.setText(String.valueOf(malaCount));


                    beadCount = 0;
                }

                textViewBeadCountNumber.setText(String.valueOf(beadCount));

            }
        });


        relativeLayoutsecondPearl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                relativeLayoutsecondPearl.startAnimation(slide_down);
                relativeLayoutsecondPearl.setVisibility(View.GONE);
                relativeLayoutfirstPearl.setVisibility(View.VISIBLE);
                relativeLayoutfirstPearl.startAnimation(slide_top_bottom);
                beadCount ++;
                if(beadCount==108){

                    malaCount++;
                    //   mala.malaCount(malaCount);
                    textViewMalaCountNumber.setText(String.valueOf(malaCount));


                    beadCount = 0;
                }

                textViewBeadCountNumber.setText(String.valueOf(beadCount));

            }
        });

//        arrayListPearlsData =  new ArrayList<>();
//        for(int i = 0;i<10000;i++){
//
//            PearlsData pearlsData = new PearlsData(i+1);
//            arrayListPearlsData.add(pearlsData);
//        }
//
//
//        pearlsAdapter = new PearlsAdapter(getActivity(),R.layout.ball_item,arrayListPearlsData,Mala.this);
//        listViewPearls.setHorizontalScrollBarEnabled(false);
//        listViewPearls.setVerticalScrollBarEnabled(false);
//        listViewPearls.setAdapter(pearlsAdapter);
    }


    private void initialiseListeners() {

        imageViewReplay.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {

            showDialog();

           }
       });


      listViewPearls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


          }
      });


        imageViewInformation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showInformationDialog();

            }
        });
    }

    private void showInformationDialog() {

//        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
//
//        dialog.setContentView(R.layout.bead_info);
//
//        ImageView imageViewDismiss = (ImageView) dialog.findViewById(R.id.imageViewDismiss);
//
//        imageViewDismiss.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//
//            public void onClick(View arg0) {
//
//                dialog.dismiss();
//
//            }
//
//        });
//
//        dialog.show();


        Intent beadActivity = new Intent(getActivity(),BeadActivity.class);
        startActivity(beadActivity);
    }

    private void showDialog() {
        //showDialog();
        final CharSequence[] items = {
                "Restart Counter", "Share Via Email", "Share via Facebook","Cancel"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Hello Hanuman");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection

                String selectedItem = (String) items[item];

                if (selectedItem.equalsIgnoreCase("Restart Counter")) {


                    dialog.dismiss();
                    beadCount = 0;
                    malaCount = 0;

                    resetCounts();

                } else if (selectedItem.equalsIgnoreCase("Share Via Email")) {

                    dialog.dismiss();
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "hi@awesomeactually.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Hello Hanuman - FeedBack");
                    intent.putExtra(Intent.EXTRA_TEXT, "Sent from Android");
                    startActivity(intent);

                } else if (selectedItem.equalsIgnoreCase("Share Via Facebook")) {
                    dialog.dismiss();
                    String urlToShare = "www.jayhanuman.com";
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
                    // See if official Facebook app is found
                    boolean facebookAppFound = false;
                    List<ResolveInfo> matches = getActivity().getPackageManager().queryIntentActivities(intent, 0);
                    for (ResolveInfo info : matches) {
                        if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook")) {
                            intent.setPackage(info.activityInfo.packageName);
                            facebookAppFound = true;
                            break;  }}
                    // As fallback, launch sharer.php in a browser
                    if (!facebookAppFound) {
                        String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
                    }
                    startActivity(intent);

                } else if (selectedItem.equalsIgnoreCase("Cancel")) {

                    dialog.dismiss();

                }


            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    private void resetCounts() {

      //  textViewBeadCountNumber = (TextView)view.findViewById(R.id.textViewBeadCountNumber);
      //  textViewMalaCountNumber = (TextView)view.findViewById(R.id.textViewMalaCountNumber);
        textViewBeadCountNumber.setText(String.valueOf(beadCount));
        textViewMalaCountNumber.setText(String.valueOf(malaCount));
    }


    public void beadCount(int malaCount){


        textViewBeadCountNumber.setText(String.valueOf(malaCount));
//        if(malaCount>108){
//
//            beadCountNumber++;
//            textViewBeadCountNumber.setText(String.valueOf(beadCountNumber));
//
//        }

        //Another Logic
        //beadcount - (malacount*108)=108
        //malacount+1;
    }

    public void malaCount(int beadCount){

        textViewMalaCountNumber.setText(String.valueOf(beadCount));
    }
}
