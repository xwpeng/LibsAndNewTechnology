package com.android.xwpeng.trxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.xwpeng.trxjava.rxone.CombinateOpration;
import com.android.xwpeng.trxjava.rxone.CreateOpration;
import com.android.xwpeng.trxjava.rxone.FilterOpration;
import com.android.xwpeng.trxjava.rxone.RxBase;
import com.android.xwpeng.trxjava.rxone.TransferOpration;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        RxBase.aa();
//        RxBase.cc();
//        CreateOpration.interval();
//        CreateOpration.range();
//        CreateOpration.repeat();
//        TransferOpration.map();
//        TransferOpration.flatmap();
//        TransferOpration.flatmap2();
//        TransferOpration.cast();
//        TransferOpration.concatMap();
//        TransferOpration.buffer();
//        TransferOpration.buffer2();
//        TransferOpration.buffer3();
//        TransferOpration.window();
//        TransferOpration.groupBy();
//        TransferOpration.groupBy2();
//        FilterOpration.filter();
//        FilterOpration.elementAt();
//        FilterOpration.distinct();
//        FilterOpration.distinct2();
//        FilterOpration.distinctUntilChanged();
//        FilterOpration.distinctUntilChanged2();
//        FilterOpration.skip();
//        FilterOpration.skip2();
//        FilterOpration.skipLast();
//        FilterOpration.take();
//        FilterOpration.take2();
//        FilterOpration.takeLast();
//        FilterOpration.takeUntil();
//        FilterOpration.takeUntil2();
//        FilterOpration.ignoreElements();
//        FilterOpration.throttleFirst();
//        FilterOpration.throttleWithTimeout();
//        FilterOpration.debounce();
//        FilterOpration.debounce2();
//        CombinateOpration.startWith();
//        CombinateOpration.merge();
//        CombinateOpration.mergeWith();
//        CombinateOpration.concat();
//        CombinateOpration.zip();
        CombinateOpration.combineLatest();
    }

}
