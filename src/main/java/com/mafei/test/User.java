package com.mafei.test;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Setter
@Getter
public class User {
    @ValidateField
    @Max(value = 18, message = "年龄必须大于等于18岁")
    private int age;
}