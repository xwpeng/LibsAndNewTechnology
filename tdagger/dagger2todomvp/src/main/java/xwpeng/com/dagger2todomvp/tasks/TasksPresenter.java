package xwpeng.com.dagger2todomvp.tasks;

import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import xwpeng.com.dagger2todomvp.data.Task;
import xwpeng.com.dagger2todomvp.data.TasksRepository;

public class TasksPresenter implements TasksContract.Presenter {

    public TasksPresenter(TasksRepository tasksRepository, TasksContract.View view) {
        Log.e("xwpeng17", view.hashCode() + "");
//        view.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadTasks(boolean forceUpdate) {

    }

    @Override
    public void addNewTask() {

    }

    @Override
    public void openTaskDetails(@NonNull Task requestedTask) {

    }

    @Override
    public void completeTask(@NonNull Task completedTask) {

    }

    @Override
    public void activateTask(@NonNull Task activeTask) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void takeView(TasksContract.View view) {

    }

    @Override
    public void dropView() {

    }
}
