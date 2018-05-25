package com.android.xwpeng.trxjava.rxone;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * 测试创建操作符
 * Created by xwpeng on 2018/5/8.
 */

public class CreateOpration {
    private static final String TAG = CreateOpration.class.getSimpleName();

    /***interval操作符*/
    public static void interval() {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(aLong -> {
            Log.e(TAG, "" + aLong);
        }, throwable -> {

        }, () -> {

        });
    }

    /*** range操作符*/
    public static void range() {
        Observable.range(1, 5).subscribe(integer -> {
            Log.e(TAG, integer + "");
        });
    }

    /*** repeat操作符*/
    public static void repeat() {
        Observable.range(1, 5).repeat(5).subscribe(integer -> {
            Log.e(TAG, integer + "");
        });
    }


}
