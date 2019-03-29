package xwpeng.com.dagger2todomvp.data;

import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;


public class TasksRepository implements TasksDataSource {

    public TasksRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {

    }

    @Inject
    public void aa(){
        Log.e("xwpeng16", "kkkkk");
    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    @Override
    public void completeTask(@NonNull Task task) {

    }

    @Override
    public void completeTask(@NonNull String taskId) {

    }

    @Override
    public void activateTask(@NonNull Task task) {

    }

    @Override
    public void activateTask(@NonNull String taskId) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

    }
}
