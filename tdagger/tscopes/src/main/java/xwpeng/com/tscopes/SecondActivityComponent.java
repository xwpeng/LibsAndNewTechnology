package xwpeng.com.tscopes;

import dagger.Component;

/**
 * Created by xwpeng on 2019/1/16.
 */
@ActivityScope
@Component(dependencies = PotComponent.class)
public interface SecondActivityComponent {
    void inject(SecondActivity secondActivity);
}
