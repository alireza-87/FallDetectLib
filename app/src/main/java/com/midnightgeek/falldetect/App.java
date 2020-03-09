package com.midnightgeek.falldetect;

import android.app.Application;

import com.midnightgeek.falllib.LibLoader;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LibLoader.builder().setContext(getApplicationContext()).setStayAwake(true).setShowNotification(true).build();
    }
}
