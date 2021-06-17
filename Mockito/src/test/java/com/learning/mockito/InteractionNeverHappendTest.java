package com.learning.mockito;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class InteractionNeverHappendTest {

    @Test
    void validateNoInteractionTest() {

        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);

        //using mocks - only mockOne is interacted
        mockOne.add("one");

        //ordinary verification
        verify(mockOne).add("one");

        //verify that method was never called on a mock
        verify(mockOne, never()).add("two");

        //verify that other mocks were not interacted
        verifyNoInteractions(mockTwo, mockThree);

    }

    @Test
    void validateNoMoreInteractionsTest() {

        List mockedList = mock(List.class);

        //using mocks
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");
        verify(mockedList).add("two");

        //following verification will fail if interactions are not verified.
        verifyNoMoreInteractions(mockedList);
    }

    @Test
    void validateNoInteractionsUsingNeverTest() {

        List mockedList = mock(List.class);

        //using mocks
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");
        verify(mockedList).add("two");

        //following verification will fail if interactions are not verified.
        verify(mockedList, never()).add("three");

    }
}
