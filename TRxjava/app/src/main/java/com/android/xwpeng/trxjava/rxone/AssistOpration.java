package com.android.xwpeng.trxjava.rxone;

import android.os.SystemClock;
import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Notification;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 辅助操作符
 * Created by xwpeng on 2018/6/8.
 */

public class AssistOpration {
    private static final String TAG = AssistOpration.class.getSimpleName();

    /**
     * delay操作符
     */
    public static void delay() {
        Observable<Long> ob = Observable.create((Observable.OnSubscribe<Long>) subscriber -> {
            subscriber.onNext(System.currentTimeMillis());
            subscriber.onCompleted();
        }).delay(10, TimeUnit.SECONDS);
        ob.subscribe(integer -> Log.e(TAG, "" + (System.currentTimeMillis() - integer)));
    }

    /**
     * do操作符doOnEach
     */
    public static void doOnEach() {
        Observable<String> ob = Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext("1");
//            subscriber.onCompleted();
        }).doOnEach(notification -> Log.e(TAG, "call " + notification)).doOnEach(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, " doOnEach onCompleted");

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, " doOnEach onError");

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, " doOnEach onNext");

            }
        });
        ob.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext");
            }
        });
    }

    /**
     * do操作符doOnNext
     */
    public static void doOnNext() {
        Observable<String> ob = Observable.create(subscriber -> {
            subscriber.onNext("1");
        });
        ob = ob.doOnNext(s -> Log.e(TAG, "doOnNext"));
        ob.subscribe(s -> Log.e(TAG, s));
    }

    /**
     * do操作符doOnError
     */
    public static void doOnError() {
        Observable<String> ob = Observable.create(subscriber -> {
            subscriber.onNext("1");
            subscriber.onError(new Throwable("aaa"));
        });
        ob = ob.doOnError(s -> Log.e(TAG, "doOnError"));
        ob.subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()));
    }

    /**
     * do操作符doOnCompleted
     */
    public static void doOnCompleted() {
        Observable<String> ob = Observable.create(subscriber -> {
            subscriber.onNext("1");
            subscriber.onCompleted();
        });
        ob = ob.doOnCompleted(() -> Log.e(TAG, "doOnCompleted"));
        ob.subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "subscribe OnCompleted"));
    }

    /**
     * do操作符doOnTerminate
     */
    public static void doOnTerminate() {
        Observable<String> ob = Observable.create(subscriber -> {
            subscriber.onNext("1");
//            subscriber.onCompleted();
            subscriber.onError(new Throwable("aaa"));
        });
        ob = ob.doOnTerminate(() -> Log.e(TAG, "doOnTerminate"));
        ob.subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "subscribe OnCompleted"));
    }

    /**
     * do操作符finallyDo
     */
    public static void finallyDo() {
        Observable<String> ob = Observable.create(subscriber -> {
            subscriber.onNext("1");
            subscriber.onCompleted();
//            subscriber.onError(new Throwable("aaa"));
        });
        ob = ob.finallyDo(() -> Log.e(TAG, "finallyDo"));
        ob.subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "subscribe OnCompleted"));
    }

    /**
     * do操作符doAfterTerminate
     */
    public static void doAfterTerminate() {
        Observable<String> ob = Observable.create(subscriber -> {
            subscriber.onNext("1");
//            subscriber.onCompleted();
//            subscriber.onError(new Throwable("aaa"));
        });
        ob = ob.doAfterTerminate(() -> Log.e(TAG, "doAfterTerminate"));
        ob.subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "subscribe OnCompleted"));
    }

    /**
     * do操作符doOnSubsribe
     */
    public static void doOnSubsribe() {
        Observable<String> ob = Observable.create(subscriber -> {
            subscriber.onNext("1");
        });
        ob = ob.doOnSubscribe(() -> Log.e(TAG, "doOnSubsribe"));
        ob.subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "subscribe OnCompleted"));
    }

    /**
     * do操作符doOnUnsubscribe
     */
    public static void doOnUnsubscribe() {
        Observable<String> ob = Observable.create(subscriber -> {
            subscriber.onNext("1");
//            subscriber.onError(new Throwable("aaa"));
//            throw  new Exception("aaaaa");
//           subscriber.onCompleted();
        });
        ob = ob.doOnUnsubscribe(() -> Log.e(TAG, "doOnUnsubscribe"));
        ob.subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "subscribe OnCompleted"));
        ob.subscribe(s -> Log.e(TAG, "2 " + s), throwable -> Log.e(TAG, "2 " + throwable.getMessage()), () -> Log.e(TAG, "2 subscribe OnCompleted"));

    }

    /**
     * subscribeOn操作符
     */
    public static void subscribeOn() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext("1");
            Log.e(TAG, "source  " + Thread.currentThread().getName());
        })
                .doOnSubscribe(() -> {
                    Log.e(TAG, "doOnSubsribe1");
                    Log.e(TAG, "doOnSubsribe1  " + Thread.currentThread().getName());
                })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(() -> {
                    Log.e(TAG, "doOnSubsribe2");
                    Log.e(TAG, "doOnSubsribe2  " + Thread.currentThread().getName());
                })
//                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
//                .subscribeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.immediate())
//                .subscribeOn(Schedulers.trampoline())
//       .doOnSubscribe(() -> Log.e(TAG, "doOnSubsribe"))
                .subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()), () -> Log.e(TAG, "subscribe OnCompleted"));
    }

    public static void subscribeOn2() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext("1");
            Log.e(TAG, "source  " + Thread.currentThread().getName());
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "onnext  " + Thread.currentThread().getName());
                    }
                });
    }

    /**
     * observeOn操作符
     */
    public static void observeOn() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext("1");
        }).map(s -> {
            Log.e(TAG, "map1 " + Thread.currentThread().getName());
            return "map1";
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(s -> {
                    Log.e(TAG, "map2 " + Thread.currentThread().getName());
                    return "map2";
                })
                .observeOn(Schedulers.computation())
                .map(s -> {
                    Log.e(TAG, "map3 " + Thread.currentThread().getName());
                    return "map3";
                })
                .map(s -> {
                    Log.e(TAG, "map4 " + Thread.currentThread().getName());
                    return "map4";
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.e(TAG, s);
                    Log.e(TAG, "accpte next in " + Thread.currentThread().getName());
                });
    }

    /**
     * timeout操作符
     */
    public static void timeout() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            for (int i = 0; i < 10; i++) {
                SystemClock.sleep(i * 1000);
                subscriber.onNext("" + i);
            }
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io())
                .timeout(3, TimeUnit.SECONDS, Observable.just("error"))
                .subscribe(s -> Log.e(TAG, s), throwable -> Log.e(TAG, throwable.getMessage()));
    }

}
