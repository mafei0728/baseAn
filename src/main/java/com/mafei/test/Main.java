package com.mafei.test;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        ValidationProxy validationProxy = new ValidationProxy(user);
        user.setAge(20);
    }
}
