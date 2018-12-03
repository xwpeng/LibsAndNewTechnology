package xwpeng.com.tcomponent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xwpeng on 2018/11/29.
 */
@Module
public class PotModule {
    @Provides
    Pot providePot(@RoseFlower Flower flower) {
        return new Pot(flower);
    }
}
