package com.android.xwpeng.trxjava.rxone;

import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

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

    /**
     * combineLastest操作符
     */
    public static void combineLatest2() {
        Observable<Integer> observable1 = Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            for (int i = 1; i < 4; i++) {
                SystemClock.sleep(200);
                subscriber.onNext(i);
            }
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
        Observable<String> observable2 = Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            SystemClock.sleep(300);
            subscriber.onNext("a");
            SystemClock.sleep(300);
            subscriber.onNext("b");
            SystemClock.sleep(300);
            subscriber.onNext("c");

            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());
        Observable.combineLatest(observable1, observable2, new Func2<Integer, String, String>() {
            @Override
            public String call(Integer integer, String s) {
                return integer + s;
            }
        }).subscribe(s -> Log.e(TAG, s));
    }

    /**
     * switchOnNext操作符
     * switchOnNext2才是正确的操作，小的Observable也要延时才有效果
     */
    public static void switchOnNext() {
        Observable.switchOnNext(
                Observable.just(Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
                    try {
                        subscriber.onNext(1);
                        Thread.currentThread().sleep(1000);
                        subscriber.onNext(2);
                        Thread.currentThread().sleep(1000);
                        subscriber.onNext(3);
                        Thread.currentThread().sleep(2000);
                        subscriber.onNext(4);
                        subscriber.onCompleted();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).subscribeOn(Schedulers.io()), Observable.create((Observable.OnSubscribe<String>) subscriber -> {
                    try {
                        Thread.currentThread().sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext("a");
                    subscriber.onCompleted();
                }).subscribeOn(Schedulers.io()))
        ).subscribe(s -> Log.e(TAG, String.valueOf(s)));
    }

    public static void switchOnNext2() {
        //每隔500毫秒产生一个observable
        Observable<Observable<Long>> observable = Observable.interval(0, 500, TimeUnit.MILLISECONDS).map(aLong -> {
            //每隔200毫秒产生一组数据（0,10,20,30,40)
            return Observable.interval(0, 200, TimeUnit.MILLISECONDS).map(aLong1 -> aLong1 * 10).take(5);
        }).take(2);

        Observable.switchOnNext(observable).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.err.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                System.err.println("Next:" + aLong);
            }
        });
    }

    public static void join() {
        Observable<String> observable = Observable.interval(10, TimeUnit.SECONDS).map(new Func1<Long, String>() {
            @Override
            public String call(Long aLong) {
                return String.valueOf(aLong);
            }
        });
        Func1<String, Observable<Long>> leftDu = s -> Observable.timer(20, TimeUnit.SECONDS);
        Func1<String, Observable<Long>> rightDu = s -> Observable.timer(30, TimeUnit.SECONDS);
        Observable.interval(10, TimeUnit.SECONDS)
                .map(aLong -> String.valueOf(aLong) + " -- ")
                .join(observable, leftDu, rightDu, (s1, s2) -> s1 + s2).subscribe(s -> Log.e(TAG, s));
    }


}
