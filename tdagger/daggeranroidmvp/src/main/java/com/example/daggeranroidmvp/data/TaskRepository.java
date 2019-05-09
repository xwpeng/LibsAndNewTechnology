package com.example.daggeranroidmvp.data;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TaskRepository {
    @Inject
    public TaskRepository(){

    }

    public String getTasks(){
        return "获取到的任务列表";
    }
}
