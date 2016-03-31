package com.example.shiva.hanumanji;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.hanumanji.R;

import java.util.LinkedHashMap;

/**
 * Created by shiva on 22/2/16.
 */
public class PearlActivity extends Activity
{


    private LinearLayout relativeLayoutfirstPearl;
    private LinearLayout relativeLayoutsecondPearl;
    ImageView imageViewSecondPearl;

    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ball_new_item);

        final Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);

        final Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up);

        final Animation slide_top_bottom = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_out_up);

        final Animation zoomout = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoomout);


        relativeLayoutfirstPearl = (LinearLayout)findViewById(R.id.relativeLayoutFirstPearl);
        relativeLayoutsecondPearl = (LinearLayout)findViewById(R.id.relativeLayoutSecondPearl);
        imageViewSecondPearl = (ImageView)findViewById(R.id.imageViewSecondPearl);
        relativeLayoutfirstPearl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relativeLayoutfirstPearl.startAnimation(slide_down);
                relativeLayoutfirstPearl.setVisibility(View.GONE);
                relativeLayoutsecondPearl.setVisibility(View.VISIBLE);
                relativeLayoutsecondPearl.startAnimation(slide_top_bottom);

                }
                });


        relativeLayoutsecondPearl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                relativeLayoutsecondPearl.startAnimation(slide_down);
                relativeLayoutsecondPearl.setVisibility(View.GONE);
                relativeLayoutfirstPearl.setVisibility(View.VISIBLE);
                relativeLayoutfirstPearl.startAnimation(slide_top_bottom);

            }
        });



    }

    public void slideToBottom(RelativeLayout view){

        TranslateAnimation animate = new TranslateAnimation(0,0,0,view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
        relativeLayoutsecondPearl.setVisibility(View.VISIBLE);
    }
}
