package com.learning;

import java.util.Random;

public class IdGenerator {


    public static long generateNewId() {

        Random random = new Random();
        return random.nextLong();
    }
}
