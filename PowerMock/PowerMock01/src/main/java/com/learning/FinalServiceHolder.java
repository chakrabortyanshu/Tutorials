package com.learning;

import java.util.HashSet;
import java.util.Set;

public class FinalServiceHolder {

    private final Set services = new HashSet();

    public final void addService(Object service) {
        services.add(service);
    }

    public final void removeService(Object service) {
        services.remove(service);
    }

    public final static void getString(){
        System.out.println("Inside getString()");
    }
}
