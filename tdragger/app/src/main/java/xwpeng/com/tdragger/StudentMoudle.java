package xwpeng.com.tdragger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xwpeng on 2018/11/19.
 */
@Module
public class StudentMoudle {
    @Provides
    public String provideName() {
        return "provide";
    }

//    @Provides
////    public String provideId() {
////        return "2";
////    }

    @Provides
    public int provideScore() {
        return 90;
    }

    @Provides
    Student provideStudent(String name, String id, int score) {
        return new Student(name, id, score);
    }
}
