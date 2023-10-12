package com.mafei.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.Set;

public class ValidationProxy implements MethodInterceptor {

    private final Object target;
    private final Validator validator;

    private ValidationProxy(Object target) {
        this.target = target;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (isGetterMethod(method)) {
            validateGetter(method);
        }
        return method.invoke(target, args);
    }

    private boolean isGetterMethod(Method method) {
        return method.getName().startsWith("get") || method.getName().startsWith("is");
    }

    private void validateGetter(Method method) {
        Set<ConstraintViolation<Object>> violations = validator.validateProperty(target, getPropertyName(method));
        if (!violations.isEmpty()) {
            throw new ValidationException(violations.iterator().next().getMessage());
        }
    }

    private String getPropertyName(Method method) {
        String methodName = method.getName();
        if (methodName.startsWith("get")) {
            return methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
        } else if (methodName.startsWith("is")) {
            return methodName.substring(2, 3).toLowerCase() + methodName.substring(3);
        }
        throw new IllegalArgumentException("Invalid getter method name");
    }

    public static <T> T createValidatingProxy(T target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new ValidationProxy(target));
        return (T) enhancer.create();
    }

    private static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }
}
