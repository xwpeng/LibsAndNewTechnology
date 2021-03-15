package com.xwpeng.tkotlin.aop;

public class ManSayHelloWorld implements ISayHelloWorld {
    @Override
    public String say() {
        System.out.println("Hello world!");
        return "MAN";
    }
}
