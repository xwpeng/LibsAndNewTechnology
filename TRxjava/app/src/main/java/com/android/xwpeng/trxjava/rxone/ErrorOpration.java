package com.android.xwpeng.trxjava.rxone;

import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * 错误处理操作符
 * Created by xwpeng on 2018/6/11.
 */

public class ErrorOpration {
    private static final String TAG = ErrorOpration.class.getSimpleName();

    /**
     * onErrorReturn操作符
     */
    public static void onErrorReturn() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
//            subscriber.onError(new Exception("aaa"));
            for (int i = 0; i < 10; i++) {
                SystemClock.sleep(i * 1000);
                subscriber.onNext("" + i);
            }
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io())
                .timeout(2, TimeUnit.SECONDS)
                .onErrorReturn(throwable -> throwable.toString())
                .subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "accpte oncompeted"));
    }

    /**
     * onErrorReturn操作符
     */
    public static void onErrorResumeNext() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
//            subscriber.onError(new Exception("aaa"));
            for (int i = 0; i < 10; i++) {
                SystemClock.sleep(i * 1000);
                subscriber.onNext("" + i);
            }
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io())
                .timeout(2, TimeUnit.SECONDS)
                .onErrorResumeNext(throwable -> Observable.just("a", "b", "c"))
                .subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "accpte oncompeted"));
    }

    /**
     * onExceptionResumeNext操作符
     */
    public static void onExceptionResumeNext() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onError(new Throwable("aaa"));
            for (int i = 0; i < 10; i++) {
                SystemClock.sleep(i * 1000);
                subscriber.onNext("" + i);
            }
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io())
                .timeout(2, TimeUnit.SECONDS)
                .onExceptionResumeNext(Observable.just("a", "b"))
                .subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "accpte oncompeted"));
    }

    /**
     * retry操作符
     */
    public static void retry() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
//            subscriber.onError(new Throwable("aaa"));
            for (int i = 0; i < 10; i++) {
                SystemClock.sleep(i * 1000);
                if (i > 1) subscriber.onError(new Error("custom"));
                subscriber.onNext("" + i);
            }
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io())
                .retry((integer, throwable) -> {
                    Log.e(TAG, "integer " + integer);
                    return integer % 2 == 0;
                })
                .subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "accpte oncompeted"));
    }

    /**
     * retryWhen操作符
     */
    public static void retryWhen() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                if (i > 5) subscriber.onError(new Error("custom"));
                subscriber.onNext("" + i);
            }
        })
                .retryWhen(observable -> observable.flatMap((Func1<Throwable, Observable<?>>) throwable -> Observable.just(1)))
                .subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "accpte oncompeted"));
    }



}
