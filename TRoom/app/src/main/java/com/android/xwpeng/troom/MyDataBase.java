package com.android.xwpeng.troom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.android.xwpeng.troom.bean.Class;
import com.android.xwpeng.troom.bean.Student;
import com.android.xwpeng.troom.dao.ClassDao;
import com.android.xwpeng.troom.dao.StudentDao;

/**
 * Created by xwpeng on 2018/7/13.
 */
@Database(entities = {Student.class, Class.class}, version = 1)
public abstract class MyDataBase extends RoomDatabase {
    public abstract StudentDao studentDao();
    public abstract ClassDao classDao();
}
