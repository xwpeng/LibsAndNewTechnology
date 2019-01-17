package com.xwpeng.tsubcomponent;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by xwpeng on 2019/1/16.
 */
public class MainActivity extends AppCompatActivity{
    @Inject
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcmponent_main);
        DaggerAppComponent.builder().build()
                .subMainActiivty()
                .inject(this);
        Toast.makeText(context, "show content", Toast.LENGTH_SHORT).show();
        Log.e("xwpeng16", "inject context: " + context.toString());
    }
}
