package com.android.xwpeng.troom.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.android.xwpeng.troom.bean.Student;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by xwpeng on 2018/7/12.
 */
@Dao
public interface StudentDao {
    @Query("SELECT * FROM STUDENT")
    List<Student> getAll();
    @Query("SELECT * FROM STUDENT")
    Flowable<List<Student>> getAllRx();
    @Query("SELECT * From STUDENT where id in (:ids)")
    List<Student> getAllByIds(long[] ids);
    @Insert(onConflict = OnConflictStrategy.ROLLBACK)
    List<Long> insert(Student... students);
    @Delete
    void delete(Student student);
    @Update(onConflict = OnConflictStrategy.ROLLBACK)
    int update(Student student);
    @Query("SELECT * FROM STUDENT where class_id = :classId")
    List<Student> queryClassByClass(int classId);
}
