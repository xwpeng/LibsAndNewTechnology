package xwpeng.com.tcomponents.module;

import dagger.Module;
import dagger.Provides;
import xwpeng.com.tcomponents.bean.Flower;
import xwpeng.com.tcomponents.bean.Lily;
import xwpeng.com.tcomponents.bean.Rose;
import xwpeng.com.tcomponents.qualifier.LilyQualifier;
import xwpeng.com.tcomponents.qualifier.RoseQualifier;


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
