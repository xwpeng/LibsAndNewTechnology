package xwpeng.com.tdragger;

import dagger.Component;

/**
 * Created by xwpeng on 2018/11/19.
 */
@Component(modules = StudentMoudle.class)
public interface StudentComponent {
    void inject(MainActivity activity);
}
