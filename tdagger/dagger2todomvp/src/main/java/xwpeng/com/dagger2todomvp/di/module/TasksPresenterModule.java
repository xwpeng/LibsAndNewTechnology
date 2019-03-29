package xwpeng.com.dagger2todomvp.di.module;

import dagger.Module;
import dagger.Provides;
import xwpeng.com.dagger2todomvp.di.ActivityScope;
import xwpeng.com.dagger2todomvp.data.TasksRepository;
import xwpeng.com.dagger2todomvp.tasks.TasksContract;
import xwpeng.com.dagger2todomvp.tasks.TasksPresenter;

@Module
public class TasksPresenterModule {
    // tasksRepository 实例由 AppComponent 提供
    // 而 view 实例由 TasksActivityComponent.Builder 中的 view(TasksContract.View view) 方法提供
    @Provides
    @ActivityScope
    TasksPresenter provideTasksPresenter(TasksRepository tasksRepository, TasksContract.View view) {
        return new TasksPresenter(tasksRepository, view);
    }
}
