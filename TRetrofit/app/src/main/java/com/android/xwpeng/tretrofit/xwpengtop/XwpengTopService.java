package com.android.xwpeng.tretrofit.xwpengtop;


import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.Cookie;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by xwpeng on 2018/6/28.
 */

public interface XwpengTopService {
    @POST("auth/login")
    Flowable<Result<User>> login(@Body LoginInfo loginInfo);

    @POST("village_hand/add")
    @Multipart
    Flowable<Result> villageHandAdd(@Part MultipartBody.Part photo, @PartMap Map<String, RequestBody> requestBodyMap, @Header("Cookie") String cookie);

    @POST("village_hand/add")
    @Multipart
    Flowable<Result> villageHandAdd2(@PartMap Map<String, RequestBody> requestBodyMap, @Header("Cookie") String cookie);
}
