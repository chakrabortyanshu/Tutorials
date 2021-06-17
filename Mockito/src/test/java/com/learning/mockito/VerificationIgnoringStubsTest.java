package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class VerificationIgnoringStubsTest {

    @Test
    void verificationTest() {
        Person mock = Mockito.mock(Person.class);
        Person mockTwo = Mockito.mock(Person.class);

        mock.getName();
        mockTwo.getName();


        verify(mock).getName();
        verify(mockTwo).getName();

        //ignores all stubbed methods:
        verifyNoMoreInteractions(ignoreStubs(mock, mockTwo));

        //creates InOrder that will ignore stubbed
        InOrder inOrder = inOrder(ignoreStubs(mock, mockTwo));
        inOrder.verify(mock).getName();
        inOrder.verify(mockTwo).getName();
        inOrder.verifyNoMoreInteractions();

    }
}
