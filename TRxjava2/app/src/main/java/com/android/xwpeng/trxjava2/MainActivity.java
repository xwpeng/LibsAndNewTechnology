package com.android.xwpeng.trxjava2;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
//private static final prsf
//    public static final String psfs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//          tPublish();
//        tPublish2();
//        tReactivePull();
//        tSingle();
//        tMaybe();
//        tCompletable();
//        TReactivePull.tSync();
        TReactivePull.tAsync();
        TReactivePull.request(196);
    }


    private void nomal() {
        Flowable.create((FlowableOnSubscribe<String>) emitter -> emitter.onComplete(), BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(subscription -> {

                })
                .subscribe(s -> {
                    throw new Exception("aaa");

                }, throwable -> {

                }, () -> {

                }, subscription -> {
                });
    }

    private void tPublish() {
        ConnectableFlowable<Integer> cF = Flowable.interval(0, 1, TimeUnit.SECONDS)
                .map(aLong -> aLong.intValue())
                .publish(2);
        Disposable disposable = cF.connect();
        cF.subscribe(s -> {
            Log.e(TAG, "one: " + s);
        });
        Flowable.timer(5, TimeUnit.SECONDS).observeOn(Schedulers.newThread()).map(aLong -> {
            disposable.dispose();
            Log.e(TAG, Thread.currentThread().getName());
            return aLong;
        }).subscribe();

        Flowable.timer(5, TimeUnit.SECONDS).map(aLong -> {
            cF.connect();
            cF.take(5).subscribe(s -> {
                Log.e(TAG, "two: " + s);
            });
            return aLong;
        }).subscribe();


    }

    private void tPublish2() {
        ConnectableFlowable<Integer> cF = Flowable.interval(0, 1, TimeUnit.SECONDS)
                .map(aLong -> aLong.intValue())
                .publish(2);
        cF.connect();
        Disposable disposable = cF.subscribe(s -> {
            Log.e(TAG, "one: " + s);
        });
        cF.subscribe(s -> {
            Log.e(TAG, "two: " + s);
        });
        Flowable.timer(5, TimeUnit.SECONDS).map(aLong -> {
            disposable.dispose();
            return aLong;
        }).subscribeOn(Schedulers.newThread()).subscribe();
    }

    Subscription subscription;
    private void tReactivePull() {
        Flowable.interval(0, 1, TimeUnit.SECONDS).onBackpressureLatest().subscribe(s -> {
            Log.e(TAG, "one: " + s);
            subscription.request(1);
        }, throwable -> {

        }, () -> {

        }, subs -> {
            subscription = subs;
            subscription.request(1);
        });
    }

    private void tMaybe() {
        Maybe.just(1)
                .map(v -> v + 1)
                .filter(v -> v == 1)
                .defaultIfEmpty(2)
                .subscribe(s -> {
                    Log.e(TAG, "one: " + s);
                });
    }

    private void tSingle() {
        Single.create(new SingleOnSubscribe<Object>() {
            @Override
            public void subscribe(SingleEmitter<Object> emitter) throws Exception {
                emitter.onError(new Throwable("aaa"));
            }
        }).subscribe(s -> {
          Log.e(TAG, s  + "");
        }, Throwable::printStackTrace);
    }

    private void tCompletable(){
        Completable.create(emitter -> emitter.onComplete()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }
        });
    }
}
