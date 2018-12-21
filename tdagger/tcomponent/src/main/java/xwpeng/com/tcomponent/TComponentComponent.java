package xwpeng.com.tcomponent;

import dagger.Subcomponent;

/**
 * Created by xwpeng on 2018/11/29.
 */
//@Component(modules = {PotModule.class, FlowerMoudle.class})
//@Component(dependencies = PotComponent.class)
@Subcomponent
public interface TComponentComponent {
    void inject(TComponetActivity TComponetActivity);
}
