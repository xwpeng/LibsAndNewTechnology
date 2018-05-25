package com.android.xwpeng.trxjava.rxone;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.subjects.Subject;

import static rx.Observable.create;
import static rx.Observable.just;

/**
 * Rx1.0基础
 * Created by xwpeng on 2018/4/23.
 */

public class RxBase {
    private static final String TAG = RxBase.class.getSimpleName();

    /**
     * 观察者（订阅者）Subscriber
     * Observable被观察者
     * 实验四个回调事件在事件队列中的表现
     * 创建观察者，事件回调 -> 创建被观察者，事件调用 -> 被观察者订阅观察者
     */
    public static void aa() {
        //创建观察者，回调处理
        Subscriber subscriber = new Subscriber() {
            @Override
            public void onStart() {
                super.onStart();
                //事件队列将开始自动触发。可做准备性的工作，如准备数据。无需手动
                //最多一次
                Log.e(TAG, "onStart: ");
            }

            @Override
            public void onNext(Object o) {
                //表示一个普通的事件。
                // 可多次。
                Log.e(TAG, "onNext: " + o.toString());
            }

            @Override
            public void onCompleted() {
                //表示事件队列完结，不会继续处理其他事件。手动调用此方法表示队列结束。
                // 事件队列中最后一个事件处理完，不会自动回调。
                //最多一次
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                //事件队列发生异常时，会自动触发onError,并且不会继续处理其他事件。也可自动调用
                // 最多一次
                Log.e(TAG, "onError: " + e.getMessage());
            }
        };

        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext("aaa");
                subscriber.onError(new Exception("error1"));
                subscriber.onError(new Exception("error2"));
                subscriber.onCompleted();
                subscriber.onNext("bbb");
            }
        }).subscribe(subscriber);

    }

    /**
     * 最原始的观察者Observer，只有三个回调
     * Subscriber实现了它，是它的扩充
     */
    public static void bb() {
        Observer observer = new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        };
    }

    /**
     * Observable被观察者太复杂，定义了很多LINQ操作符
     * 实验just与from
     */
    public static void cc() {
        Observable<String> a = Observable.just("1", "2", "3");
        create(su -> {

        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {

            }
        });
        just("1", "2", "3").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "call: " + s);
            }
        });
        Observable.from(new String[]{"1", "2", "3"}).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "call: " + s);
            }
        });
    }

    /**
     * 测试主题
     */
    public static void dd() {
        Subject.create(subscriber -> {
        }).subscribe();
    }
}
