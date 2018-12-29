package xwpeng.com.tproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xwpeng on 2018/12/25.
 */
public class ProxyHandler implements InvocationHandler {
    private Object realSubject;

    public ProxyHandler(Object realSubject) {
        this.realSubject = realSubject;
    }
    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.out.println("all will shit");
        Object result = method.invoke(realSubject, args);
        System.out.println("asshole");
        return result;
    }
}
