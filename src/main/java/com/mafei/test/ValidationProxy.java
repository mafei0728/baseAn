package com.mafei.test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationProxy {

    private final Object target;
    private final Validator validator;

    public ValidationProxy(Object target) {
        this.target = target;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public void setProperty(String propertyName, Object value) {
        // 在设置属性值时进行校验
        validateProperty(propertyName, value);
        // 设置属性值
        setPropertyWithoutValidation(propertyName, value);
    }

    private void validateProperty(String propertyName, Object value) {
        Set<ConstraintViolation<Object>> violations = validator.validateValue((Class<Object>) target.getClass(), propertyName, value);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations.iterator().next().getMessage());
        }
    }

    private void setPropertyWithoutValidation(String propertyName, Object value) {
        // 这里可以根据需要进行其他设置逻辑
        // 例如，使用反射设置属性值
        // 反射设置属性的示例：
        // Field field = target.getClass().getDeclaredField(propertyName);
        // field.setAccessible(true);
        // field.set(target, value);
    }

    private static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }
}