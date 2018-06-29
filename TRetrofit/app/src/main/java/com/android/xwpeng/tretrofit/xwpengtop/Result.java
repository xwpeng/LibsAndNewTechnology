package com.android.xwpeng.tretrofit.xwpengtop;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by xwpeng on 2018/6/28.
 */

public class Result<T> {
    @JSONField(name = "var")
    public T bean;
    public String code;
}
