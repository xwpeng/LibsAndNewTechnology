package xwpeng.com.tproxy;

import java.lang.reflect.Proxy;

/**
 * Created by xwpeng on 2018/12/25.
 */
public class DynamicProxy {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        //同时我们一定要记住，通过 Proxy.newProxyInstance 创建的代理对象是在jvm运行时动态生成的一个对象，
        // 它并不是我们的InvocationHandler类型，也不是我们定义的那组接口的类型，而是在运行是动态生成的一个对象，并且命名方式都是这样的形式，以$开头，proxy为中，最后一个数字表示对象的标号。
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader()
                , realSubject.getClass().getInterfaces()
                , new ProxyHandler(realSubject));
        proxySubject.doSomething();
    }
}
