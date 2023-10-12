package com.mafei.tx;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationProcessor {

    static {
        processAnnotations(YourClass.class);
    }

    public static void init() {}
    public static void processAnnotations(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                try {
                    long startTime = System.currentTimeMillis();

                    // 执行静态方法
                    method.invoke(null);

                    long endTime = System.currentTimeMillis();
                    long executionTime = endTime - startTime;

                    System.out.println(method.getName() + " executed in " + executionTime + " ms");
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        processAnnotations(YourClass.class);
    }
}