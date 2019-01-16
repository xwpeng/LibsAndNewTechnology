package com.xwpeng.tsubcomponent;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xwpeng on 2019/1/16.
 */
@Module
public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    @Provides
    public static Context getContext() {
        Log.e("xwpeng16", "provide context: " + context.toString());
        return context;
    }
}
