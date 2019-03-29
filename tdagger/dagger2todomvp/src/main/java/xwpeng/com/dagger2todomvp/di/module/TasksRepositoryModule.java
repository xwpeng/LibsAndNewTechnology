package xwpeng.com.dagger2todomvp.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xwpeng.com.dagger2todomvp.data.LocalDataSource;
import xwpeng.com.dagger2todomvp.data.RemoteDataSource;
import xwpeng.com.dagger2todomvp.data.TasksRepository;

@Module
public class TasksRepositoryModule {
//    @Provides
//    @Singleton
//    LocalDataSource provideTasksLocalDataSource(Context context) {
//        return new LocalDataSource(context);
//    }
//
//    @Provides
//    @Singleton
//    RemoteDataSource provideTasksRemoteDataSource() {
//        return new RemoteDataSource();
//    }

    @Provides
    @Singleton
    TasksRepository provideTasksRepository(Context context) {
        return new TasksRepository(new LocalDataSource(context), new RemoteDataSource());
    }

}
