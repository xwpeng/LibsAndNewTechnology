package com.xwpeng.tmapkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainActivityComponent.create().inject(this);
        Log.e("xwpeng16", map.size() + "");
    }
}
