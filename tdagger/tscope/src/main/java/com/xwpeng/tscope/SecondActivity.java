package com.xwpeng.tscope;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by xwpeng on 2019/1/17.
 */
public class SecondActivity extends AppCompatActivity {
    @Inject
    Pot mPot3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scope_main);
        DaggerSecondActivityComponent.builder().potComponent(DaggerPotComponent.create())
                .build().inject(this);
        Log.e("SecondActivity", "pot3 hashcode : " + mPot3.hashCode());
    }
}
