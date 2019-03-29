package xwpeng.com.tcomponents.module;

import dagger.Module;
import dagger.Provides;

import xwpeng.com.tcomponents.bean.Flower;
import xwpeng.com.tcomponents.bean.Pot;
import xwpeng.com.tcomponents.qualifier.RoseQualifier;

/**
 * Created by xwpeng on 2018/11/29.
 */
@Module
public class PotModule {
    @Provides
    Pot providePot(@RoseQualifier Flower flower) {
        return new Pot(flower);
    }
}
