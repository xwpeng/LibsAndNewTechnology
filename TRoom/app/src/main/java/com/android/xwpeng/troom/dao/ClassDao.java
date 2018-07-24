package com.android.xwpeng.troom.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.android.xwpeng.troom.bean.Class;

import java.util.List;

/**
 * Created by xwpeng on 2018/7/16.
 */
@Dao
public interface ClassDao {
    @Insert
    List<Long> insert(Class... classes);
}
