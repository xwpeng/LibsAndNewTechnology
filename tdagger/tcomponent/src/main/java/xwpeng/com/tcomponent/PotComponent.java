package xwpeng.com.tcomponent;

import dagger.Subcomponent;

/**
 * Created by xwpeng on 2018/11/29.
 */
//@Component(modules = {PotModule.class, FlowerMoudle.class})
//@Component(modules = PotModule.class,dependencies = FlowerComponent.class)
@Subcomponent(modules = PotModule.class)
public interface PotComponent {
    //    Pot getPot();
//      MainActivityComponent getSub();
}
