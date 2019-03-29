package xwpeng.com.dagger2todomvp;

import android.app.Application;

import xwpeng.com.dagger2todomvp.di.component.AppComponent;

import xwpeng.com.dagger2todomvp.di.component.DaggerAppComponent;
import xwpeng.com.dagger2todomvp.di.module.AppModule;

public class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }


    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
