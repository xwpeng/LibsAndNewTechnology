package com.example.daggeranroidmvp.ui;

import com.example.daggeranroidmvp.data.TaskRepository;

import javax.inject.Inject;


public class FristFrgmentPresenter implements FristFragmentContract.Presenter {
    private TaskRepository taskRepository;
    private FristFragmentContract.View mView;

    @Inject
    public FristFrgmentPresenter(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public void takeView(FristFragmentContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void showTasks() {
     mView.showTasks(taskRepository.getTasks());
    }
}
