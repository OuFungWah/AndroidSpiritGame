package com.example.androidspiritgame.application;

import android.app.Application;

/**
 * Created by 区枫华 on 2017/a5/16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHolder.initial(this);
    }
}
