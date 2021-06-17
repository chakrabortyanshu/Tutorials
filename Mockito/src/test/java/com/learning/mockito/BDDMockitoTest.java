package com.learning.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class BDDMockitoTest {

    @Test
    void bddMockingTest() {

        Person person = Mockito.mock(Person.class);

        BDDMockito.given(person.getName()).willReturn("EFGH");

        Assertions.assertEquals("EFGH",person.getName());

        BDDMockito.verify(person).getName();

    }

    @Test
    void bddMockingTest2() {
        Person person = Mockito.mock(Person.class);

        //Given
        BDDMockito.given(person.getName()).willReturn("XYZ");

        //When
        String name = person.getName();
        String name2 = person.getName();

        //Then
        Assertions.assertEquals("XYZ",name);
        Assertions.assertNotEquals("EFAB",name2);

        //Then
        BDDMockito.then(person).should(times(2)).getName();
    }
}
