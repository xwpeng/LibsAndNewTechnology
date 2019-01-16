package xwpeng.com.tcomponent;

import dagger.Component;
import xwpeng.com.tcomponent.bean.Pot;
import xwpeng.com.tcomponent.module.PotModule;

/**
 * Created by xwpeng on 2018/11/29.
 */
//@Component(modules = {PotModule.class, FlowerMoudle.class})
@Component(modules = PotModule.class,dependencies = FlowerComponent.class)
public interface PotComponent {
        Pot bbPot();
}
