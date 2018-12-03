package xwpeng.com.tcomponent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xwpeng on 2018/11/29.
 */
@Module
public class FlowerMoudle {
    @RoseFlower
    @Provides
    Flower provideRose() {
        return new Rose();
    }

    @LilyFlower
    @Provides
    Flower provideLily() {
        return new Lily();
    }
}
