package com.mafei.tx;

class YourClass {

    @LogExecutionTime
    public static void yourStaticMethod() {
        System.out.println("Executing yourStaticMethod...");
        // Your business logic here
    }

    public static void main(String[] args) {
        AnnotationProcessor.init();
        yourStaticMethod();
    }
}