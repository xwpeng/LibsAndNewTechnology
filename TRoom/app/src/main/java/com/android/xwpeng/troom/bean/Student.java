package com.android.xwpeng.troom.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.IntDef;

/**
 * 学生
 * Created by xwpeng on 2018/7/12.
 */
@Entity(tableName = "student"
        , indices = {@Index(value = {"name"}, unique = true)
        , @Index(value = "class_id", unique = true)}
        , foreignKeys = @ForeignKey(entity = Class.class
        , parentColumns = "id"
        , childColumns = "class_id"
        , onDelete = ForeignKey.SET_NULL)
)
public class Student {
    @PrimaryKey
    public int id;
    public String name;
    public boolean sex;
    @Ignore
    public String nickName;
    @ColumnInfo(name = "class_id")
    public int classId;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", nickName='" + nickName + '\'' +
                ", classId=" + classId +
                '}';
    }
}


