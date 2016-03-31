package com.example.shiva.hanumanji;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by DELL on 14-02-2016.
 */
public class MyApplication extends Application {




    private String dayId;
    private static MyApplication singleton;
    @Override
    public void onCreate()
    {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        singleton = this;
        // Initialize the singletons so their instances
        // are bound to the application process.
    }


    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public static MyApplication getInstance(){
        return singleton;
    }

}
