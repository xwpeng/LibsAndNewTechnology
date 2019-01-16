package xwpeng.com.tcomponent;

import dagger.Component;
import xwpeng.com.tcomponent.module.FlowerMoudle;
import xwpeng.com.tcomponent.module.PotModule;

/**
 * Created by xwpeng on 2019/1/16.
 */
@Component(dependencies = PotComponent.class)
//@Component(modules = {PotModule.class, FlowerMoudle.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
