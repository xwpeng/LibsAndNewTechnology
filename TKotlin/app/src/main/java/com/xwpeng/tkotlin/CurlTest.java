package com.xwpeng.tkotlin;

public class CurlTest {
    static {
        System.loadLibrary("jni_curl");
    }

    public static native int curlTest();
}
