package com.example.daggeranroidmvp.di.module;

import com.example.daggeranroidmvp.di.FragmentScope;
import com.example.daggeranroidmvp.ui.FristFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FirstFragmentContriModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = FirstFragmentModule.class)
    abstract FristFragment contributeYourActivityInjector();
}
