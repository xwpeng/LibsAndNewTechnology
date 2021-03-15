package com.xwpeng.tkotlin.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AOPHandle implements InvocationHandler {
    private Object object;

    public AOPHandle(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前面做事");
        Object o = method.invoke(object, args);
        System.out.println("后面做事");
        return o;
    }

    public static void main(String[] args) {
        ISayHelloWorld iSayHelloWorld = new ManSayHelloWorld();
        AOPHandle aopHandle = new AOPHandle(iSayHelloWorld);
        ((ISayHelloWorld)Proxy.newProxyInstance(ManSayHelloWorld.class.getClassLoader(), new Class[]{ISayHelloWorld.class}
        , aopHandle)).say();
    }
}
