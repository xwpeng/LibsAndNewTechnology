package com.example.daggeranroidmvp.di.module;

import com.example.daggeranroidmvp.di.ActivityScope;
import com.example.daggeranroidmvp.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AllActivityMoudle {
    @ActivityScope
    @ContributesAndroidInjector(modules = FirstFragmentContriModule.class)
    abstract MainActivity contributeMainActivity();
}
