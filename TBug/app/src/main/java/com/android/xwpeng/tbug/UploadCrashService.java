package com.android.xwpeng.tbug;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static io.reactivex.BackpressureStrategy.DROP;

/**
 * Created by xwpeng on 2018/6/14.
 */

public class UploadCrashService extends Service {
    private boolean mUploading;

    public UploadCrashService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mUploading) return super.onStartCommand(intent, flags, startId);
        uploadCrash(intent.getStringExtra("path"));
        return super.onStartCommand(intent, flags, startId);
    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    private void uploadCrash(final String path) {
        mUploading = true;
        File file = new File(path);
        Flowable.timer(1, TimeUnit.SECONDS).flatMap(aLong -> uploadCrash(file))
                .retry()
                .doFinally(this::stopSelf)
                .subscribe(res -> {
                    Log.e("xwpeng", res);
                    file.delete();
                    mUploading = false;
                }, Throwable::printStackTrace);
    }

    private Flowable<String> uploadCrash(File file) {
        return Flowable.create(emitter -> {
            if (!isNetworkConnected(getApplication())) emitter.onError(new Throwable());
            OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS).build();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("attach", file.getName(), RequestBody.create(MediaType.parse("text"), file))
                    .build();
            Request request = new Request.Builder().url("http://xwpeng.top:90/improve/app_crash_log/add")
                    .post(requestBody)
                    .build();
            ResponseBody responseBody = okHttpClient.newCall(request).execute().body();
            String response = responseBody == null ? "server error" : responseBody.string();
            Log.e("xwpeng", response);
            emitter.onNext(response);
            emitter.onComplete();
        }, DROP);
    }
}
