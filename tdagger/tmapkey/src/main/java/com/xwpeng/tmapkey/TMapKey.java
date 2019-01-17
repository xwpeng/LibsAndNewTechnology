package com.xwpeng.tmapkey;

import dagger.MapKey;

/**
 * Created by xwpeng on 2019/1/17.
 */
@MapKey
public @interface TMapKey {
    String value();
}
