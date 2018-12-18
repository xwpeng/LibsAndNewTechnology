package com.android.xwpeng.trxjava2;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xwpeng on 2018/6/26.
 */

public class TReactivePull {
    public static final String TAG = TReactivePull.class.getSimpleName();
    private static Subscription mSubscription;

    public static void tSync() {
        Flowable.create((FlowableOnSubscribe<String>) emitter -> {
            Log.e(TAG, "requested: " + emitter.requested());
            emitter.onNext("1");
            emitter.onNext("2");
            emitter.onNext("3");
        }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(2);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });
    }


    public static void tAsync() {
        Flowable.create((FlowableOnSubscribe<String>) emitter -> {
            Log.e(TAG, "requested: " + emitter.requested());
           while (true) {
               if (emitter.requested() > 0) {
                   Log.e(TAG, "emitter: " + emitter.requested());
                   emitter.onNext("" + emitter.requested());
               }
           }
//           emitter.onComplete();
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(String s) {
//                        Log.e(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e(TAG, "onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });
    }

    public static void request(int n) {
        if (mSubscription != null)
        mSubscription.request(n);
    }
}
