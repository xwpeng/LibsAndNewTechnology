package xwpeng.com.tcomponents;


import dagger.Component;
import xwpeng.com.tcomponents.bean.Pot;
import xwpeng.com.tcomponents.module.PotModule;

/**
 * Created by xwpeng on 2018/11/29.
 */
//@Component(modules = {PotModule.class, FlowerMoudle.class})
@Component(modules = PotModule.class,dependencies = FlowerComponent.class)
public interface PotComponent {
        Pot bbPot();
}
