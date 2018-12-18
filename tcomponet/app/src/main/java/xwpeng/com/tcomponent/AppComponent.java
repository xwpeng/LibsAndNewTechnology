package xwpeng.com.tcomponent;

import dagger.Component;

/**
 * Created by xwpeng on 2018/12/6.
 */
@Component(modules = App.class)
public interface AppComponent {
    MainActivityComponent getSub();
}
