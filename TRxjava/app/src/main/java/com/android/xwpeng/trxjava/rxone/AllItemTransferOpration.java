package com.android.xwpeng.trxjava.rxone;

import android.util.Log;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * 转换操作符
 * Created by xwpeng on 2018/6/11.
 */

public class AllItemTransferOpration {
    private static final String TAG = AllItemTransferOpration.class.getSimpleName();

    /*** toList操作符*/
    public static void toList() {
        Observable.range(1, 5).toList().subscribe(ls -> {
            Log.e(TAG, ls.toString());
        });
    }

    /*** toSortedList操作符*/
    public static void toSortedList() {
        Observable.just(1, 5, 6).toSortedList().subscribe(ls -> {
            Log.e(TAG, ls.toString());
        });
    }

    /*** toSortedList操作符*/
    public static void toSortedList2() {
        Observable.just(1, 5, 6).toSortedList(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer < integer2 ? 1 : -1;
            }
        }).subscribe(ls -> {
            Log.e(TAG, ls.toString());
        });
    }

    /*** toMap操作符*/
    public static void toMap() {
        Observable.just(1, 5, 6).toMap(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "key " + String.valueOf(integer);
            }
        }).subscribe(ls -> {
            Log.e(TAG, ls.toString());
        });
    }

    /*** toMap操作符*/
    public static void toMap2() {
        Observable.just(1, 5, 6).toMap(integer -> "key " + String.valueOf(integer), new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "V " + integer;
            }
        }).subscribe(ls -> {
            Log.e(TAG, ls.toString());
        });
    }

    /*** toMap操作符*/
    public static void toMap3() {
        Observable.just(1, 5, 6).toMap(
                integer -> "key " + String.valueOf(integer)
                , integer -> "V " + integer
                , (Func0<Map<String, String>>) () -> {
                    HashMap hashMap = new HashMap();
                    hashMap.put("init", "xwpeng");
                    return hashMap;
                }
        ).observeOn(AndroidSchedulers.mainThread()).subscribe(ls -> {
            Log.e(TAG, ls.toString());
        });
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        }).subscribe();
    }


}
