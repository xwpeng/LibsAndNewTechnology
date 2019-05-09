package com.example.daggeranroidmvp.di.module;

import com.example.daggeranroidmvp.data.Student;
import com.example.daggeranroidmvp.di.FragmentScope;
import com.example.daggeranroidmvp.ui.FristFragmentContract;
import com.example.daggeranroidmvp.ui.FristFrgmentPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public  abstract class FirstFragmentModule {
    @FragmentScope
    @Provides
    static Student getStudent() {
        Student student = new Student();
        student.name = "xwpeng";
        student.age = 27;
        return student;
    }

    @FragmentScope
    @Binds
    abstract FristFragmentContract.Presenter getPresenter(FristFrgmentPresenter presenter);

}
