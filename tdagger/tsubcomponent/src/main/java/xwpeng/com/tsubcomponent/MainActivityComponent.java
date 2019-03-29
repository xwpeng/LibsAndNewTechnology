package xwpeng.com.tsubcomponent;

import dagger.Subcomponent;

/**
 * Created by xwpeng on 2019/1/16.
 */
@Subcomponent
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
