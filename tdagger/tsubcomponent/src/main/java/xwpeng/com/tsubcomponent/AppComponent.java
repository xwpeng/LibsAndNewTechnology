package xwpeng.com.tsubcomponent;

import dagger.Component;

/**
 * Created by xwpeng on 2019/1/16.
 */
@Component(modules = AppMudule.class)
public interface AppComponent {
      MainActivityComponent subMainActiivty();
}
