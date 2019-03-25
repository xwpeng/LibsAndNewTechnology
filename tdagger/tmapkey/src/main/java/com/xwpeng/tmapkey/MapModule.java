package com.xwpeng.tmapkey;

import java.lang.reflect.Type;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Created by xwpeng on 2019/1/17.
 */
@Module
public class MapModule {
    @Provides
    @IntoMap
    @TMapKey("foo")
    public String foo(){
        return "foo value";
    }

    @Provides
    @IntoMap
    @TMapKey("bar")
    public String bar(){
        return "bar value";
    }

}
