package com.learning.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class StubbingMethodWithExceptionTest {

    @Test
    void stubWithExceptionTest() {

        List mockedList = mock(List.class);

        doThrow(new RuntimeException()).when(mockedList).clear();

        //following throws RuntimeException:
        Assertions.assertThrows(RuntimeException.class,() -> mockedList.clear());
    }
}
