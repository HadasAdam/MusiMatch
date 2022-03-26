package com.example.musimatch;

import android.app.Application;
import android.content.Context;

public class MusiMatchApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
