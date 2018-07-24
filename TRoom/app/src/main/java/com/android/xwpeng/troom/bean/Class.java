package com.android.xwpeng.troom.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by xwpeng on 2018/7/16.
 */
@Entity(tableName = "student_class")
public class Class {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
}
