package com.xwpeng.tscope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by xwpeng on 2019/1/16.
 */
@Component(dependencies = PotComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
