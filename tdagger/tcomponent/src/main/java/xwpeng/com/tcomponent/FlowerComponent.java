package xwpeng.com.tcomponent;

import dagger.Component;
import xwpeng.com.tcomponent.bean.Flower;
import xwpeng.com.tcomponent.module.FlowerMoudle;
import xwpeng.com.tcomponent.qualifier.LilyQualifier;
import xwpeng.com.tcomponent.qualifier.RoseQualifier;

/**
 * Created by xwpeng on 2018/11/29.
 */
@Component(modules = FlowerMoudle.class)
public interface FlowerComponent {
    @RoseQualifier
    Flower getRoseFlower();
    @LilyQualifier
    Flower getLilyFlower();
}
