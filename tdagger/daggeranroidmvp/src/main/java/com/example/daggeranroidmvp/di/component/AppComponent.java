package com.example.daggeranroidmvp.di.component;

import android.app.Application;

import com.example.daggeranroidmvp.App;
import com.example.daggeranroidmvp.di.module.AllActivityMoudle;
import com.example.daggeranroidmvp.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {AppModule.class, AndroidInjectionModule.class, AllActivityMoudle.class})
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

}
