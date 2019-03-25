package xwpeng.com.tdagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xwpeng on 2018/11/24.
 */
@Module
public class FlowerModule {
    //带参注入
    private String name;

    @Provides
    String nameProvider(){
        return name;
    }

    FlowerModule(String name) {
        this.name = name;
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface IsLily {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface IsRose {
    }

    @Provides
//    @Named("Lily")
    @IsLily
    Flower provideLily() {
        return new Lily();
    }

    @Provides
//    @Named("Rose")
    @IsRose
    Flower provideRose(String name) {
        return new Rose();
    }


}
