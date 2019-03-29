package xwpeng.com.dagger2todomvp.di.component;

import javax.inject.Singleton;

import dagger.Component;
import xwpeng.com.dagger2todomvp.di.module.AppModule;
import xwpeng.com.dagger2todomvp.di.module.TasksRepositoryModule;

@Singleton
@Component(modules = {AppModule.class, TasksRepositoryModule.class})
public interface AppComponent {
    TasksActivityComponent.Builder tasksActivityComponent();
}
