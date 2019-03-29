package xwpeng.com.tcomponents;

import dagger.Component;
import xwpeng.com.tcomponents.bean.Flower;
import xwpeng.com.tcomponents.module.FlowerMoudle;
import xwpeng.com.tcomponents.qualifier.LilyQualifier;
import xwpeng.com.tcomponents.qualifier.RoseQualifier;

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
