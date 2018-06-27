package com.android.xwpeng.trxjava.rxone;

import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 布尔操作符
 * Created by xwpeng on 2018/6/11.
 */

public class BooleanOpration {
    public static final String TAG = BooleanOpration.class.getSimpleName();

    /**
     * all操作符
     */
    public static void all() {
        Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                subscriber.onNext(i);
            }
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io())
                .all(s -> s > -1)
                .subscribe(s -> {
                    Log.e(TAG, s + "");

                }, throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "accpte oncompeted"));
    }

    /**
     * contains操作符
     */
    public static void contains() {
        Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                subscriber.onNext(i);
            }
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io())
                .map(integer -> integer + "")
                .contains(1)
                .subscribe(s -> Log.e(TAG, s + ""), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "accpte oncompeted"));
    }

    /**
     * isEmpty操作符
     */
    public static void isEmpty() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
//                 subscriber.onCompleted();
            subscriber.onError(new Throwable("aaa"));
            }
        }).subscribeOn(Schedulers.io())
                .isEmpty()
                .subscribe(s -> Log.e(TAG, s + ""), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "accpte oncompeted"));
    }




}
