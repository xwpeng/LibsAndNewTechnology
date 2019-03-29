package xwpeng.com.tandroiddagger.di.module;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import xwpeng.com.tandroiddagger.data.Student;
import xwpeng.com.tandroiddagger.ui.FristFragment;
import xwpeng.com.tandroiddagger.ui.MainActivity;

@Module
public abstract class MainActivity2Module {
    //    @ActivityScope
    @ContributesAndroidInjector(modules = {FirstFragmentMoudle.class})
    abstract MainActivity contributeYourActivityInjector();
}
