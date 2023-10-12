package com.mafei.aop;

public class Main {
    public static void main(String[] args) {
        User user = ValidationProxy.createValidatingProxy(new User());

        // 设置合法的年龄
        user.setAge(20);

        // 调用 getter 方法，不会抛出异常
        int age = user.getAge();
        System.out.println("年龄：" + age);

        // 设置不合法的年龄
        user.setAge(15);

        // 调用 getter 方法，会抛出异常
        age = user.getAge();
    }
}
