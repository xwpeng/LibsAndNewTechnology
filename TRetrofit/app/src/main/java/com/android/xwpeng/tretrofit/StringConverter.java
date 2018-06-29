package com.android.xwpeng.tretrofit;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by xwpeng on 2018/6/28.
 */

public class StringConverter implements Converter<ResponseBody, String> {
    public static final StringConverter INSTANCE = new StringConverter();
    @Override
    public String convert(ResponseBody value) throws IOException {
        return value.string();
    }
}
