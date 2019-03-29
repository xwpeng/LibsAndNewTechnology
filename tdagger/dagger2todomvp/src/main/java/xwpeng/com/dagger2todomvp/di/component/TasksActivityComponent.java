package xwpeng.com.dagger2todomvp.di.component;

import dagger.BindsInstance;
import dagger.Subcomponent;
import xwpeng.com.dagger2todomvp.di.ActivityScope;
import xwpeng.com.dagger2todomvp.tasks.TasksActivity;
import xwpeng.com.dagger2todomvp.tasks.TasksContract;
import xwpeng.com.dagger2todomvp.di.module.TasksPresenterModule;

@ActivityScope
@Subcomponent(modules = {TasksPresenterModule.class})
public interface TasksActivityComponent {
    void inject(TasksActivity tasksActivity);

    @Subcomponent.Builder
    interface Builder {
        //带参数注入
        @BindsInstance
        Builder view(TasksContract.View view);

        TasksActivityComponent build();
    }
}
