package com.android.xwpeng.troom.test;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Database;
import android.util.Log;

import com.android.xwpeng.troom.MyDataBase;
import com.android.xwpeng.troom.bean.Class;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 基于课程类的简单增删改查
 * Created by xwpeng on 2018/9/20.
 */
public class SampleClassTest {
    @SuppressLint("CheckResult")
    public static void insertClass(MyDataBase myDataBase) {
        Flowable.create((FlowableOnSubscribe<List<Long>>)emitter -> {
            Class myClass = new Class();
            myClass.name = "实验班1";
            Class class2 = new Class();
            class2.name = "实验班2";
            emitter.onNext(myDataBase.classDao().insert(myClass, class2));
            emitter.onComplete();
        }, BackpressureStrategy.ERROR)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(s -> {
                    Log.e("xwpeng16", "insert success ids : " + s.toString() );
                });
    }

    @SuppressLint("CheckResult")
    public static void queryAllClass(MyDataBase myDataBase) {
        Flowable.create((FlowableOnSubscribe<List<Class>>) emitter -> {
            emitter.onNext(myDataBase.classDao().queryAll());
            emitter.onComplete();
        }, BackpressureStrategy.ERROR)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(s -> {
                    Log.e("xwpeng16", s.toString());
                });

    }

    @SuppressLint("CheckResult")
    public static void delete(MyDataBase myDataBase, Class... c) {
        Flowable.create((FlowableOnSubscribe<Integer>)emitter -> {
//            myDataBase.getOpenHelper().getReadableDatabase().delete();
            emitter.onNext(myDataBase.classDao().delete(c));
            emitter.onComplete();
        }, BackpressureStrategy.ERROR)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(s -> {
                    Log.e("xwpeng16", s.toString());
                }, throwable -> {
                    throwable.printStackTrace();
                });

    }
}
