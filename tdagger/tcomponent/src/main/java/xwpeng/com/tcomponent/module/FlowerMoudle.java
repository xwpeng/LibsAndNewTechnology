package xwpeng.com.tcomponent.module;

import dagger.Module;
import dagger.Provides;
import xwpeng.com.tcomponent.bean.Flower;
import xwpeng.com.tcomponent.bean.Lily;
import xwpeng.com.tcomponent.bean.Rose;
import xwpeng.com.tcomponent.qualifier.LilyQualifier;
import xwpeng.com.tcomponent.qualifier.RoseQualifier;

/**
 * Created by xwpeng on 2018/11/29.
 */
@Module
public class FlowerMoudle {
    @RoseQualifier
    @Provides
    Flower provideRose() {
        return new Rose();
    }

    @LilyQualifier
    @Provides
    Flower provideLily() {
        return new Lily();
    }
}
