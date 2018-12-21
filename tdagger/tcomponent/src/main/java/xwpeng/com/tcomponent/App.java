package xwpeng.com.tcomponent;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xwpeng on 2018/12/6.
 */
@Module
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Provides
    public String provideApp() {
        return "aa";
    }
}
