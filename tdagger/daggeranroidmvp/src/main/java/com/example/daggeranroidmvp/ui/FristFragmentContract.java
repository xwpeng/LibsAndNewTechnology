package com.example.daggeranroidmvp.ui;

import com.example.daggeranroidmvp.BasePresenter;
import com.example.daggeranroidmvp.BaseView;

public interface FristFragmentContract {
    interface Presenter extends BasePresenter<View> {
        void showTasks();
    }

    interface View extends BaseView<Presenter> {
        void showTasks(String tasksString);
    }
}
