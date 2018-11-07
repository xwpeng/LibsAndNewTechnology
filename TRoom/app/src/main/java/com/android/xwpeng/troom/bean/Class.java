package com.android.xwpeng.troom.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * 班级
 * Created by xwpeng on 2018/7/16.
 */
@Entity(tableName = "class")
public class Class {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
