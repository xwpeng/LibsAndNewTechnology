package xwpeng.com.tandroiddagger.di.module;

import dagger.Module;
        import dagger.Provides;
        import dagger.android.ContributesAndroidInjector;
        import xwpeng.com.tandroiddagger.data.Student;
        import xwpeng.com.tandroiddagger.ui.FristFragment;

@Module
public abstract class FirstFragmentMoudle {
    @ContributesAndroidInjector()
    abstract FristFragment contributeYourActivityInjector();

    @Provides
    static Student getStudent(){
        Student student = new Student();
        student.name = "xwpeng";
        student.age = 27;
        return student;
    }
}
