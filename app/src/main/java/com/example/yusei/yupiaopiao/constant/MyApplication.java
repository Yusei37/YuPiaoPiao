package com.example.yusei.yupiaopiao.constant;

import android.app.Application;
import android.content.Context;

/**
 * Created by yusei on 2017/12/9
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
