package com.xwpeng.tscope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by xwpeng on 2019/1/16.
 */

@Component(modules = PotModule.class)
public interface PotComponent {
    Pot getPot();
}
