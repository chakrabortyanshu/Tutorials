package com.learning;

public class PartialMockClass {

    public PartialMockClass() {
    }

    public String execute(){
        return "execute() "+ privateMethodToMock();
    }

    private String privateMethodToMock(){
        return "message from private String methodToMock()";
    }
}
