package com.learning.mockito;

public class Person {
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Setting variable name "+ name);
        this.name = name;
    }
}
