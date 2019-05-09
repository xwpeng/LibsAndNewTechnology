package com.example.daggeranroidmvp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.example.daggeranroidmvp.BaseActivity;
import com.example.daggeranroidmvp.R;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class MainActivity extends BaseActivity implements HasSupportFragmentInjector {
//    @Inject
//    FristFragment mFristFragment;
    @Inject
    Context mContext;

    private TextView mContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentTv = findViewById(R.id.tv_content);
        mContentTv.setText(mContext.toString() + "\n");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout_fragment, new FristFragment())
        .addToBackStack(null)
        .commit();
    }

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
