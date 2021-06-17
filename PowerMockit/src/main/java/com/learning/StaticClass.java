package com.learning;

public class StaticClass {

    public StaticClass() {
    }

    public static String staticMethod() {
        return "Static Method";
    }

    public static int staticIntMethod() {
        return -1;
    }

    private String getPrivateMethod(){
        return "returning from getPrivateMethod()";
    }

    private static String getPrivateStaticMethod(){
        return "returning from getPrivateStaticMethod()" + getPrivateStaticMethod2();
    }

    private static String getPrivateStaticMethod2(){
        return "inside getPrivateStaticMethod2()";
    }

}
