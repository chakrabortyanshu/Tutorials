package com.learning;

import java.util.HashSet;
import java.util.Set;

public class ServiceHolder {

    private final Set services = new HashSet();

    public void addService(Object service) {
        services.add(service);
    }

    public void removeService(Object service) {
        services.remove(service);
    }

    public static void getString(){
        System.out.println("Inside getString()");
    }
}
