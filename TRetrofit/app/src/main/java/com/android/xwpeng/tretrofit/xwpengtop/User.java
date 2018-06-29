package com.android.xwpeng.tretrofit.xwpengtop;

import java.util.List;

/**
 * Created by xwpeng on 2018/6/28.
 */

public class User {
    public String uid;
    public String account;
    public String region;
    public String name;
    public int status;
    public String s_token;
    public List<Role> roles;

    public static class Role {
        public int rid;
        public String name;
    }
}
