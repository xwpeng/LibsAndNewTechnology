package xwpeng.com.tcomponents;

import dagger.Component;


/**
 * Created by xwpeng on 2019/1/16.
 */
@Component(dependencies = PotComponent.class)
//@Component(modules = {PotModule.class, FlowerMoudle.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
