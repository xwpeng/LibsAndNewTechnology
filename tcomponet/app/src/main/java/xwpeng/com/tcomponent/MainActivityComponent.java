package xwpeng.com.tcomponent;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by xwpeng on 2018/11/29.
 */
//@Component(modules = {PotModule.class, FlowerMoudle.class})
//@Component(dependencies = PotComponent.class)
@Subcomponent
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
