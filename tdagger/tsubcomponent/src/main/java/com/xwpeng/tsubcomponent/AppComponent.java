package com.xwpeng.tsubcomponent;

import dagger.Component;

/**
 * Created by xwpeng on 2019/1/16.
 */
@Component(modules = App.class)
public interface AppComponent {
      MainActivityComponent subMainActiivty();
}
