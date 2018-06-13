package com.android.xwpeng.trxjava.rxone;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 条件操作符
 * Created by xwpeng on 2018/6/11.
 */

public class ConditionOpration {
    public static final String TAG = ConditionOpration.class.getSimpleName();

    /**
     * amb操作符
     */
    public static void amb() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
//            for (int i = 0; i < 10; i++) {
//                subscriber.onNext(i + "");
//            }
//            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io())
                .ambWith(Observable.just("a", "b").delay(1, TimeUnit.SECONDS))
                .subscribe(s -> Log.e(TAG, s + ""), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "accpte oncompeted"));
    }

    /**
     * defaultIfEmpty操作符
     */
    public static void defaultIfEmpty() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
//            for (int i = 0; i < 10; i++) {
//                subscriber.onNext(i + "");
//            }
//            subscriber.onCompleted();
            subscriber.onError(new Throwable("aaa"));
        }).subscribeOn(Schedulers.io())
                .defaultIfEmpty("a")
                .subscribe(s -> Log.e(TAG, s + ""), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "accpte oncompeted"));
    }



}
