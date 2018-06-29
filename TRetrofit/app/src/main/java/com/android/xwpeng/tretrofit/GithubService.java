package com.android.xwpeng.tretrofit;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xwpeng on 2018/6/27.
 */

public interface GithubService {
    @GET("users/{userName}")
    Call<ResponseBody> getUser(@Path("userName") String userName);
    @GET("users/{userName}")
    Call<User> getUserGson(@Path("userName") String userName);
    @GET("users/{userName}")
    Flowable<User> getUserRx(@Path("userName") String userName);
    @GET("users/{userName}")
    Flowable<Response<User>> getUserRx2(@Path("userName") String userName);
    @GET("users/{userName}")
    Flowable<Result<User>> getUserRx3(@Path("userName") String userName);
    @GET("users/{userName}")
    Flowable<String> getUserConverter(@Path("userName") String userName);
}
