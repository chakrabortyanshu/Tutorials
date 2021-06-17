package com.learning.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CustomVerifyDescriptionMessageTest {

    @Test
    void customVerifyDescriptionMessageTest() {

        Person person = Mockito.mock(Person.class);

        Mockito.when(person.getName()).thenReturn("StringValue");

        Assertions.assertEquals("StringValue", person.getName());
        Assertions.assertEquals("StringValue", person.getName());

        // will work with any verification mode
        verify(person, times(2).description("someMethod should be called twice")).getName();
    }

    @Test
    void customVerifyDescriptionMessageTest2() {

        Person person = Mockito.mock(Person.class);

        Mockito.when(person.getName()).thenReturn("StringValue");

        Assertions.assertEquals("StringValue", person.getName());

        // will print a custom message on verification failure
        verify(person, Mockito.description("This will print on failure")).getName();

    }

}
