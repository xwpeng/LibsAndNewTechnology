package xwpeng.com.tcomponent.module;

import dagger.Module;
import dagger.Provides;
import xwpeng.com.tcomponent.bean.Flower;
import xwpeng.com.tcomponent.bean.Pot;
import xwpeng.com.tcomponent.qualifier.RoseQualifier;

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
