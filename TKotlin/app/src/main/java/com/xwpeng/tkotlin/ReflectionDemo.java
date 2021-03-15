package com.xwpeng.tkotlin;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class ReflectionDemo {
    public interface StudentService<T> {
        List<T> findStudents(String name, int age);
    }

    public static abstract class BaseService<T> {
        abstract int save(T t);
    }

    public static class StudentServiceImpl extends BaseService<Student> implements StudentService<Student> {

        @Override
        public List<Student> findStudents(String name, int age) {
            System.out.println("invoke findStudents");
            return Arrays.asList(new Student("jack", 20)
                    , new Student("Rose", 20));
        }

        @Override
        int save(Student student) {
            return 0;
        }
    }

    static class Student {
        public String name;
        public int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        StudentServiceImpl studentService = new StudentServiceImpl();
        studentService.save(new Student("xx", 11));
        studentService.findStudents("jack", 20);

        //反射API调用示例
        final Class<? extends StudentServiceImpl> studentServiceClass = studentService.getClass();
        //获取内部类
        Class<?>[] classes = studentServiceClass.getDeclaredClasses();
        for (Class<?> c : classes) System.out.println(c);
        //获取注解信息
        Annotation[] annotations = studentServiceClass.getAnnotations();
        //获取类加载器
        ClassLoader classLoader = studentServiceClass.getClassLoader();
        //获取类成员变量
        Field[] fields = studentServiceClass.getFields();
        //获取类成员方法
        Method[] methods = studentServiceClass.getDeclaredMethods();
        System.out.println(methods[2].getName());
        try {
            methods[2].invoke(studentService, "jack", 20);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //获取父类泛型信息，自己的已经被泛型擦除了,擦除到类型上限
        Type[] types = studentServiceClass.getGenericInterfaces();
        for (Type type : types) System.out.println(type);
        Type type2 = studentServiceClass.getGenericSuperclass();
        System.out.println(type2);
    }
}
