package xwpeng.com.tscopes;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by xwpeng on 2019/1/16.
 */
@Singleton
@Component(modules = PotModule.class)
public interface PotComponent {
    Pot getPot();
}
