package com.mafei.aop;

import com.mafei.test.ValidateField;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Data
public class ChatPer {

    @ValidateField
    @DecimalMax(value = "12", message = "must < 12")
    @DecimalMin(value = "1", message = "must > 1")
    private double age;

    public static void main(String[] args) {
        ChatPer chatPer = ValidationProxy.createValidatingProxy(new ChatPer());
        chatPer.getAge();

    }
}
