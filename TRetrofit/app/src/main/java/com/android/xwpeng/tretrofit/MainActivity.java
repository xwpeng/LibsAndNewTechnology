package com.android.xwpeng.tretrofit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;

import com.android.xwpeng.tretrofit.fastjson.FastJsonConverterFactory;
import com.android.xwpeng.tretrofit.xwpengtop.LoginInfo;
import com.android.xwpeng.tretrofit.xwpengtop.XwpengTopService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.xwpeng.tretrofit.FileUtil.drawableToBitmap;
import static com.android.xwpeng.tretrofit.FileUtil.saveBitmapToFile;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Retrofit mXwpengRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xwpengRetrofitInit();
        findViewById(R.id.button).setOnClickListener(v -> {
//           tNomal();
//            tGsonSupport();
//            tRxSupport();
//            tRxSupport2();
//            tRxSupport3();
//            tConverter();
//            tFastJsonConvert();
            tLogin();
        });
    }

    private void xwpengRetrofitInit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
            builder.connectTimeout(600, TimeUnit.SECONDS);
            builder.writeTimeout(600, TimeUnit.SECONDS);
            builder.readTimeout(600, TimeUnit.SECONDS);
        }
        mXwpengRetrofit = new Retrofit.Builder()
                .client(builder.build())
//                .baseUrl("http://xwpeng.top:90/improve/")
                .baseUrl("http://192.168.18.205/improve/")
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private void tNomal() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();
        GithubService githubService = retrofit.create(GithubService.class);
        Call<ResponseBody> call = githubService.getUser("xwpeng");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG, response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void tGsonSupport() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GithubService githubService = retrofit.create(GithubService.class);
        Call<User> call = githubService.getUserGson("xwpeng");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e(TAG, response.body().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void tRxSupport() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                // 针对rxjava2.x
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GithubService githubService = retrofit.create(GithubService.class);
        githubService.getUserRx("xwpeng")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    Log.e(TAG, "RxSupport");
                    Log.e(TAG, user.toString());
                });
    }

    private void tRxSupport2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                // 针对rxjava2.x
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GithubService githubService = retrofit.create(GithubService.class);
        githubService.getUserRx2("xwpeng")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    Log.e(TAG, "RxSupport");
                    Log.e(TAG, user.toString());
                });
    }

    private void tRxSupport3() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                // 针对rxjava2.x
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GithubService githubService = retrofit.create(GithubService.class);
        githubService.getUserRx3("xwpeng")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    Log.e(TAG, "RxSupport");
                    Log.e(TAG, user.toString());
                });
    }

    private void tConverter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                // 针对rxjava2.x
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GithubService githubService = retrofit.create(GithubService.class);
        githubService.getUserConverter("xwpeng")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    Log.e(TAG, "RxSupport");
                    Log.e(TAG, user);
                });
    }

    private void tFastJsonConvert() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(FastJsonConverterFactory.create())
//                .addConverterFactory(StringConverterFactory.create())
                // 针对rxjava2.x
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GithubService githubService = retrofit.create(GithubService.class);
        githubService.getUserRx("xwpeng")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    Log.e(TAG, "RxSupport");
                    Log.e(TAG, user.toString());
                });
    }

    private void tLogin() {
        XwpengTopService service = mXwpengRetrofit.create(XwpengTopService.class);
        LoginInfo info = new LoginInfo();
        info.account = "xwpeng";
        info.pwd = "123456";
        info.client = "1";
        service.login(info).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                    Log.e(TAG, res.bean.toString());
                    tUploadImage(res.bean);
                }, Throwable::printStackTrace);
    }

    private void tUploadImage(com.android.xwpeng.tretrofit.xwpengtop.User user) {
        XwpengTopService service = mXwpengRetrofit.create(XwpengTopService.class);
        try {
            File iconFile = new File(getApplicationContext().getExternalCacheDir().getAbsolutePath(), "share_icon.png");
            Drawable drawable = getDrawable(R.mipmap.ic_launcher_round);
            saveBitmapToFile(iconFile, drawableToBitmap(drawable));
            Map<String, RequestBody> params = new HashMap<>();
            params.put("region", RequestBody.create(null, "430528100"));
            params.put("positions", RequestBody.create(null, "(111.123456,88.111111);"));
            params.put("happen_time", RequestBody.create(null,  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis()))));
            params.put("hazard_type", RequestBody.create(null, "1"));
            params.put("hand_method", RequestBody.create(null, "1"));
            params.put("drug_name", RequestBody.create(null, "杀虫宝"));
            params.put("position_type", RequestBody.create(null, "1"));
            params.put("pest_id", RequestBody.create(null, "1"));
            params.put("images[]" + "\";filename=\"" + "share_icon.png", RequestBody.create(MediaType.parse("image/png"), iconFile));
            params.put("images[]" + "\";filename=\"" + "share_icon2.png", RequestBody.create(MediaType.parse("image/png"), iconFile));
//            MultipartBody.Part file = MultipartBody.Part.createFormData("images[]", "share_icon.png", RequestBody.create(MediaType.parse("image/png"), iconFile));
            service.villageHandAdd2(params, "s_uid=" + user.uid + ";s_token=" + user.s_token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(res -> {
                        Log.e(TAG, res.bean.toString());
                    }, Throwable::printStackTrace);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
