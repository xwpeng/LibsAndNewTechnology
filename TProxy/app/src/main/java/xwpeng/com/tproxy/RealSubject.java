package xwpeng.com.tproxy;

/**
 * Created by xwpeng on 2018/12/25.
 */
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("call doSomething");
    }
}
