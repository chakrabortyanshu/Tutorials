package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

public class VerificationWithTimeoutTest {

    @Test
    void verificationWithTimeoutTest() {

        Person person = Mockito.mock(Person.class);

        //passes when someMethod() is called no later than within 100 ms
        //exits immediately when verification is satisfied (e.g. may not wait full 100 ms)
        verify(person, timeout(100)).getName();
        //above is an alias to:
        verify(person, timeout(100).times(1)).getName();

        //passes as soon as someMethod() has been called 2 times under 100 ms
        verify(person, timeout(100).times(2)).getName();

        //equivalent: this also passes as soon as someMethod() has been called 2 times under 100 ms
        verify(person, timeout(100).atLeast(2)).getName();


    }
}
