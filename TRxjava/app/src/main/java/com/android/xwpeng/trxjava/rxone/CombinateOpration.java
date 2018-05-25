package com.android.xwpeng.trxjava.rxone;

import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func2;

/**
 * 组合操作符
 * Created by xwpeng on 2018/5/25.
 */

public class CombinateOpration {
    private static final String TAG = CombinateOpration.class.getSimpleName();

    /**
     * startWith操作符
     */
    public static void startWith() {
        Observable.just(1, 2, 3).startWith(4, 5, 6)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * merge操作符
     */
    public static void merge() {
        Observable.merge(Observable.just(1, 2, 3), Observable.just(1, 2))
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * mergeWith操作符
     */
    public static void mergeWith() {
        Observable.just(1, 2, 4).mergeWith(Observable.just(1, 2, 4))
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * merge操作符
     */
    public static void concat() {
        Observable.concat(Observable.just(1, 2, 3), Observable.just(1, 2))
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * zip操作符
     */
    public static void zip() {
        Observable.zip(Observable.just(1, 2, 3), Observable.just(1, 2), new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * combineLastest操作符
     */
    public static void combineLatest() {
        Observable.combineLatest(Observable.just(1, 2, 3), Observable.just("a", "b", "c"), new Func2<Integer, String, String>() {
            @Override
            public String call(Integer integer, String s) {
                return integer + s;
            }
        }).subscribe(s -> Log.e(TAG, s));
    }
}
