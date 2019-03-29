package xwpeng.com.tscopes;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xwpeng on 2019/1/16.
 */
@Module
public class PotModule {
    @Provides
    @Singleton
    public Pot getPot() {
        return new Pot();
    }
}
