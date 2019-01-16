package com.xwpeng.tscope;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by xwpeng on 2019/1/16.
 */
public class MainActivity extends AppCompatActivity {
    @Inject
    Pot mPot1;
    @Inject
    Pot mPot2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainActivityComponent.builder()
                .potComponent(DaggerPotComponent.create())
                .build().inject(this);
        Log.e("xwpeng16", "pot1 hashcode : " + mPot1.hashCode()
        + "\n" + "pot2 hashcode : " + mPot2.hashCode());
    }
}
