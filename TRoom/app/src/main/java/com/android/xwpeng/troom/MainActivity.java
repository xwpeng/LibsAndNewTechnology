package com.android.xwpeng.troom;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.xwpeng.troom.bean.Class;
import com.android.xwpeng.troom.test.SampleClassTest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private MyDataBase mMyDataBase;
    private LinearLayout mRootLayout;
    private List<Pair<String, View.OnClickListener>> mButtonInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDb();
        mButtonInfos.add(new Pair<>("添加课程", v -> SampleClassTest.insertClass(mMyDataBase)));
        mButtonInfos.add(new Pair<>("查询所有课程", v -> SampleClassTest.queryAllClass(mMyDataBase)));
        mButtonInfos.add(new Pair<>("删除课程", v -> deleteClass()));
        initButton();
    }

    private void deleteClass() {
        Class c = new Class();
        c.id = 2;
        Class c2 = new Class();
        c2.id = 5;
        SampleClassTest.delete(mMyDataBase, c, c2);
    }

    private void initDb() {
        mMyDataBase = Room.databaseBuilder(getApplicationContext(), MyDataBase.class, "my_db")
//                .addCallback()//2个回调，创建数据库时回调，打开数据库时回调
//                .addMigrations(MIGRATION_1_2)//升级用
//                .allowMainThreadQueries()//允许在主线程查询数据库
//                .fallbackToDestructiveMigration()//升级数据库失败重新创建，而不是崩溃
//                .fallbackToDestructiveMigrationFrom()//升级数据库失败重新创建，从某个版本开始
//                .openHelperFactory() //设置打开数据库的工厂方法，如果不设置，使用默认的FrameworkSQLiteOpenHelperFactory
//                .setJournalMode()//设置日志模式，默认是自动的
                .build();
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
//                    + "`name` TEXT, PRIMARY KEY(`id`))");
        }
    };

    private void initButton() {
        mRootLayout = findViewById(R.id.root_layout);
        for (Pair<String, View.OnClickListener> buttonInfo : mButtonInfos) {
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            button.setText(buttonInfo.first);
            button.setOnClickListener(buttonInfo.second);
            mRootLayout.addView(button);
        }
    }


}
