package com.xwpeng.tmapkey;

import dagger.Component;

/**
 * Created by xwpeng on 2019/1/17.
 */
@Component(modules = MapModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
