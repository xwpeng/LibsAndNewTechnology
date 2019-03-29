package xwpeng.com.tandroiddagger.di.component;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import xwpeng.com.tandroiddagger.App;
import xwpeng.com.tandroiddagger.di.module.AppModule;
import xwpeng.com.tandroiddagger.di.module.MainActivity2Module;


@Component(modules = {AppModule.class, AndroidInjectionModule.class, MainActivity2Module.class})
public interface AppComponent {
    void inject(App app);
}
