package com.android.xwpeng.troom.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.android.xwpeng.troom.bean.Class;
import com.android.xwpeng.troom.bean.Student;

import java.util.List;

/**
 * Created by xwpeng on 2018/7/16.
 */
@Dao
public interface ClassDao {
    @Insert
    List<Long> insert(Class... classes);
    @Query("SELECT * FROM CLASS")
    List<Class> queryAll();
    //按条件删除？删除多个？，返回值是删除成功的个数,返回的类型只能是int与void
    @Delete()
    int delete(Class... c);
}
