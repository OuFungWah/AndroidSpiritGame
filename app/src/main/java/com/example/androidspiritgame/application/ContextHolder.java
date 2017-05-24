package com.example.androidspiritgame.application;

import android.content.Context;

/**
 * Created by 区枫华 on 2017/a5/16.
 */

public class ContextHolder {

    private static Context context = null;

    public static void initial(Context context1){
        context = context1;
    }

    public static Context getContext() {
        return context;
    }
}
