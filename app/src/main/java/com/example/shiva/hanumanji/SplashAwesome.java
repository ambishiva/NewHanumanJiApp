package com.example.shiva.hanumanji;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import com.app.hanumanji.R;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import java.io.File;

import constant.Constant;
import database.DataBaseHelper;

/**
 * Created by DELL on 08-02-2016.
 */
public class SplashAwesome extends Activity {


    private static int SPLASH_TIME_OUT = 1000;

    public SQLiteDatabase sqlLiteDatabase;
    private String hanumanJiDatabasePath = null;
    private File hanumanJiDatabaseFolder = null;
    final String SAMPLE_DB_NAME = Environment.getExternalStorageDirectory()+File.separator+Constant.rootFolderName
            +File.separator+Constant.databaseFolder+File.separator+"hanuapp";
    final String SAMPLE_TABLE_NAME = "time";
    DataBaseHelper dataBaseHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.splash_awesome);
        createDirectories();


        //createDatabase();

        shoeSplashForFiveSeconds();


    }

    private void createDatabase() {

        try
        {
            sqlLiteDatabase =  openOrCreateDatabase(SAMPLE_DB_NAME,MODE_PRIVATE, null);
            String CREATE_TIME_TABLE = "CREATE TABLE IF NOT EXISTS " + SAMPLE_TABLE_NAME + "( " +
                    "WEEK_DAY VARCHAR, " +
                    "HOUR VARCHAR, "+
                    "MINUTE VARCHAR )";
            sqlLiteDatabase.execSQL(CREATE_TIME_TABLE);
            sqlLiteDatabase.close();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    private void createDirectories() {

        hanumanJiDatabasePath = Environment.getExternalStorageDirectory()+File.separator+
                Constant.rootFolderName+File.separator+Constant.databaseFolder;
        hanumanJiDatabaseFolder = new File(hanumanJiDatabasePath);


        if((!hanumanJiDatabaseFolder.exists())&&(!hanumanJiDatabaseFolder.isDirectory())){
            hanumanJiDatabaseFolder.mkdirs();
        }

    }

    private void shoeSplashForFiveSeconds() {

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashAwesome.this, SplashHanumanJi.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);



    }
}
