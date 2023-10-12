package com.mafei.aop;

import javax.validation.constraints.Min;

public class User {
    private int age;

    @Min(value = 18, message = "必须大于18")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}