package com.android.xwpeng.trxjava.rxone;

import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 过滤操作符
 * Created by xwpeng on 2018/5/25.
 */

public class FilterOpration {
    private static final String TAG = FilterOpration.class.getSimpleName();

    /**
     * filter操作符
     */
    public static void filter() {
        Observable.just(1, 2, 4, 4, 7)
                .filter(integer -> integer >= 4)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * elementAt操作符
     */
    public static void elementAt() {
        Observable.just(1, 2, 4, 4, 7)
                .elementAt(1)
                .elementAtOrDefault(0, 0)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * distinct操作符
     */
    public static void distinct() {
        Observable.just(1, 2, 4, 4, 7)
                .distinct()
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * distinct操作符
     */
    public static void distinct2() {
        Observable.just(1, 2, 4, 4, 7, 4)
                .distinct(integer -> {
                    Log.e(TAG, "key: " + String.valueOf(integer / 4));
                    return String.valueOf(integer / 4);
                })
                .subscribe(integer -> {
                    Log.e(TAG, String.valueOf(integer));

                });
    }

    /**
     * distinctUntilChanged操作符
     */
    public static void distinctUntilChanged() {
        Observable.just(1, 2, 4, 4, 7, 4)
                .distinctUntilChanged()
                .subscribe(integer -> {
                    Log.e(TAG, String.valueOf(integer));
                });
    }

    /**
     * distinctUntilChanged操作符
     */
    public static void distinctUntilChanged2() {
        Observable.just(1, 2, 4, 4, 7, 4)
                .distinctUntilChanged(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        Log.e(TAG, "key: " + String.valueOf(integer / 4));
                        return String.valueOf(integer / 4);
                    }
                })
                .subscribe(integer -> {
                    Log.e(TAG, String.valueOf(integer));
                });
    }

    /**
     * skip操作符
     */
    public static void skip() {
        Observable.just(1, 2, 4, 4, 7)
                .skip(2)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * skipLast操作符
     */
    public static void skipLast() {
        Observable.just(1, 2, 4, 4, 7)
                .skipLast(2)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * skip操作符
     */
    public static void skip2() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                subscriber.onNext(i + "");
                SystemClock.sleep(1000);
            }
        }).skip(2, TimeUnit.SECONDS)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * take操作符
     */
    public static void take() {
        Observable.just(1, 2, 4, 4, 7)
                .take(2)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * takeLast操作符
     */
    public static void takeLast() {
        Observable.just(1, 2, 4, 4, 7)
                .takeLast(2)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * take操作符
     */
    public static void take2() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                subscriber.onNext(i + "");
                SystemClock.sleep(1000);
            }
        }).take(2, TimeUnit.SECONDS)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * takeUntil操作符
     */
    public static void takeUntil() {
        Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                subscriber.onNext(i);
                SystemClock.sleep(200);
            }
        }).subscribeOn(Schedulers.io())
                .takeUntil(Observable.create((Observable.OnSubscribe<String>) subscriber -> {
                    SystemClock.sleep(1000);
                    subscriber.onNext("1");
                    subscriber.onCompleted();
                }).subscribeOn(Schedulers.io()))
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * takeUntil操作符
     */
    public static void takeUntil2() {
        Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                subscriber.onNext(i);
//                SystemClock.sleep(200);
            }
        }).subscribeOn(Schedulers.io())
                .takeUntil(integer -> integer > 5)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * ignoreElements操作符
     */
    public static void ignoreElements() {
        Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                subscriber.onNext(i);
            }
//            subscriber.onError(new Throwable("error"));
            subscriber.onCompleted();
        }).ignoreElements().subscribe(
                integer -> Log.e(TAG, String.valueOf(integer)),
                throwable -> Log.e(TAG, throwable.getMessage()),
                () -> Log.e(TAG, "onCompleted")
        );
    }

    /**
     * throttleFirst操作符
     */
    public static void throttleFirst() {
        Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                subscriber.onNext(i);
                SystemClock.sleep(500);
            }
        }).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * throttleFirst操作符
     */
    public static void throttleWithTimeout() {
        Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                subscriber.onNext(i);
                SystemClock.sleep(i * 100);
            }
        }).throttleWithTimeout(300, TimeUnit.MILLISECONDS)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }


    /**
     * debounce操作符
     */
    public static void debounce() {
        Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                subscriber.onNext(i);
                SystemClock.sleep(i * 100);
            }
        }).debounce(300, TimeUnit.MILLISECONDS)
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }

    /**
     * debounce操作符
     */
    public static void debounce2() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
               for (int i = 1 ; i < 10; i ++) {
                   subscriber.onNext(i);
               }
               subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .debounce((Func1<Integer, Observable<String>>) integer -> Observable.create(subscriber -> {
                    if (integer != 2) {
//                        subscriber.onNext(integer + "");
//                        subscriber.onCompleted();
                    }
                }))
                .subscribe(integer -> Log.e(TAG, String.valueOf(integer)));
    }


}
