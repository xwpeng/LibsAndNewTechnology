package com.android.xwpeng.trxjava2.rxbus;

import java.util.List;
import java.util.Map;
import java.util.Observer;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * 不建议用Rxbus，这里仅仅是体验下
 * https://zhuanlan.zhihu.com/p/26160377
 */
public class RxBus {
    private final Subject<Object> mBus;
    private Map<Object, List<Observer>> mObserverMap;

    public static RxBus getInstnce() {
        return Inner.rxBus;
    }

    private RxBus() {
        mBus = PublishSubject.create().toSerialized();
    }

    private static class Inner {
        private static RxBus rxBus = new RxBus();
    }

    private <T> Flowable<T> getObservable(Class<T> type) {
        return mBus
                .toFlowable(BackpressureStrategy.BUFFER)
                .ofType(type);
    }

    public <T> Flowable<T> getFlowable(Class<T> type) {
        return getObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public void post(@NonNull Object object) {
        mBus.onNext(object);
    }
}


