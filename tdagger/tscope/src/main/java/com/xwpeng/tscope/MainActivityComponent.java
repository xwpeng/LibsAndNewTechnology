package com.xwpeng.tscope;

import dagger.Component;

/**
 * Created by xwpeng on 2019/1/16.
 */
@ActivityScope
@Component(dependencies = PotComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
