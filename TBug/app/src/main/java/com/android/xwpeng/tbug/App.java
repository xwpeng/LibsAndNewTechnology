package com.android.xwpeng.tbug;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * app
 * Created by xwpeng on 2018/6/13.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "f70bd4e628", false);
        CrashHandler.getInstance().init(this, BuildConfig.DEBUG, true, 0, MainActivity.class);
        LeakCanary.install(this);

    }
}
