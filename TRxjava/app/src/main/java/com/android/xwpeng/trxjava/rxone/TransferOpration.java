package com.android.xwpeng.trxjava.rxone;

import android.os.SystemClock;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * 测试变换操作符
 * Created by xwpeng on 2018/5/8.
 */

public class TransferOpration {
    private static final String TAG = TransferOpration.class.getSimpleName();

    /*** map操作符*/
    public static void map() {
        Observable.range(1, 5).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return String.valueOf(integer) + "XXX";
            }
        }).subscribe(integer -> {
            Log.e(TAG, integer + "");
        });
    }

    /*** flatmap操作符*/
    public static void flatmap() {
        Observable.range(1, 5)
                .flatMap((Func1<Integer, Observable<String>>) integer -> Observable.create(subscriber -> {
                            subscriber.onNext(-integer + "号");
                            subscriber.onNext("+" + integer + "号");
                        })
                ).subscribe(string -> Log.e(TAG, string));
    }

    /*** flatmap操作符*/
    public static void flatmap2() {
        Observable<String> observable = Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            subscriber.onNext(1);
            subscriber.onNext(2);
            subscriber.onNext(3);
            subscriber.onNext(4);
//                subscriber.onCompleted();
//                subscriber.onError(new Exception("原始异常"));
        }).flatMap(integer -> Observable.create(subscriber -> {
                    subscriber.onNext("事件" + integer);
                    SystemClock.sleep(1000);
//                    subscriber.onError(new Exception("电荷异常"));
                }), throwable -> Observable.create(subscriber -> {
                    subscriber.onNext("异常事件" + throwable.getMessage());
                    subscriber.onError(new Exception("异常事件异常"));
                }), () -> Observable.create(subscriber -> {
                    subscriber.onNext("完成事件");
                    subscriber.onError(new Exception("完成事件异常"));
                })
        );
        observable.subscribeOn(Schedulers.io()).subscribe(string -> {
            Log.e(TAG, string);
        }, throwable -> {
            Log.e(TAG, throwable.getMessage());
        });
    }

    /*** cast操作符*/
    public static void cast() {
        Observable.range(1, 5).cast(Number.class).subscribe(integer -> {
            Log.e(TAG, integer + "");
        });
    }

    /*** concatMap操作符*/
    public static void concatMap() {
        Observable.just(1,2,3)
                .concatMap(integer -> Observable.create(subscriber -> {
                    subscriber.onNext("concat" + integer);
                    subscriber.onCompleted();
                }))
                .subscribe(integer -> Log.e(TAG, integer + "")
                );
    }

    /**
     * flatMapIterable操作符
     */
    public static void flatMapIterable() {
        Observable.range(1, 5)
                .flatMapIterable(integer -> {
                    List<String> stringList = new ArrayList<>();
                    stringList.add("flatMapIterable" + integer);
                    stringList.add("flatMapIterables" + integer);
                    return stringList;
                })
                .subscribe(integer -> Log.e(TAG, integer + "")
                );
    }


    /*** buffer操作符*/
    public static void buffer() {
        Observable.range(1, 5).buffer(3).subscribe(integer -> {
            for (int i : integer)
            Log.e(TAG, i + "");
        });
    }

    /*** buffer操作符*/
    public static void buffer2() {
        Observable.range(1, 4).buffer(3, 1).subscribe(integer -> {
                Log.e(TAG, integer + "");
        });
    }

    /*** buffer操作符*/
    public static void buffer3() {
        Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onNext(i);
            }
           subscriber.onCompleted();
        }).buffer(3, TimeUnit.SECONDS, 2).subscribe(integer -> {
                Log.e(TAG, integer + "");
        }, throwable -> {

        });
    }

    /*** buffer操作符,todo 这个不懂是啥作用*/
    public static void buffer4() {
        Observable.range(1, 5).buffer(Observable.just(1)).subscribe(integer -> {
                Log.e(TAG, integer + "");
        });
    }

    /*** window操作符*/
    public static void window() {
        Observable.range(1, 5).window(2, 1).subscribe(ob -> {
            ob.subscribe(integer -> Log.e(TAG, integer + ""));
        });
    }

    /*** groupBy操作符*/
    public static void groupBy() {
        Observable.just(1,2,3,4).groupBy(o -> o % 2 == 0 ? "偶数" : "奇数").subscribe(gob -> gob.subscribe(integer -> {
            Log.e(TAG, gob.getKey() + "  ----   " + String.valueOf(integer));
        }));
    }

    /*** groupBy操作符*/
    public static void groupBy2() {
        Observable.just(1,2,3,4).groupBy(o -> o % 2 == 0 ? "偶数" : "奇数", new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return String.valueOf(integer + 1);
            }
        }).subscribe(gob -> gob.subscribe(s -> {
            Log.e(TAG, gob.getKey() + "  ----   " + s);
        }));
    }
}
