package xwpeng.com.tdragger;

import javax.inject.Inject;

/**
 * Created by xwpeng on 2018/11/19.
 */
public class Student {
    public String name;
    public String id;
    public int score;

    public Student(String name, String id, int score) {
        this.name = name;
        this.id = id;
        this.score = score;
    }

//    @Inject
    public Student() {
        this.name = "zhangsan";
        this.score = 40;
        this.id = "1";
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", score=" + score +
                '}';
    }
}
