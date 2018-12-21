package xwpeng.com.tdagger;

import dagger.Component;

/**
 * Created by xwpeng on 2018/11/23.
 */
@Component(modules = FlowerModule.class)
public interface MainActivtyComponent {
    void inject(MainActivity activity);
}
