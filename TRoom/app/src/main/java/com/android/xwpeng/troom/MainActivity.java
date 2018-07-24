package com.android.xwpeng.troom;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.xwpeng.troom.bean.Class;
import com.android.xwpeng.troom.bean.Student;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private MyDataBase mMyDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMyDataBase = Room
                .databaseBuilder(getApplicationContext(), MyDataBase.class, "my_db")
//                .addCallback()
                .addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
//                .fallbackToDestructiveMigrationFrom()
//                .openHelperFactory()
//                .setJournalMode()
                .build();
        findViewById(R.id.ok).setOnClickListener(v ->
//                new Thread(this::aa).start()
                        mMyDataBase.studentDao()
                                .getAllRx()
                                .subscribeOn(Schedulers.io())
                                 .subscribe(res -> {
                                     Log.e(TAG, res.toString());
                                 })
                );

    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
//                    + "`name` TEXT, PRIMARY KEY(`id`))");
        }
    };

    private void aa() {
//        Student student = new Student();
//        student.id = 11;
//        student.name = "xwpeng";
//        student.nickName = "xwp";
//        student.sex = true;
//        student.classId = 1;
//        Class myClass = new Class();
//        myClass.name = "实验班";
//        mMyDataBase.classDao().insert(myClass);
//        List<Long> aa = mMyDataBase.studentDao().insert(student);
//        Log.e(TAG,  mMyDataBase.studentDao().getAll().toString());
        List<Student> students = mMyDataBase.studentDao().queryClassByClass(3);
        Log.e(TAG,  students.toString());
    }
}
