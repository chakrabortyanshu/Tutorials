package com.learning;

public class ChildClass extends SuperClass{

    @Override
    public void foo() {
        super.foo();
        System.out.println("Child Class!");
    }
}
