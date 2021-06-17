package com.learning.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.when;

public class StubbingConsecutiveCallsTest {
    @Test
    void stubConsecutiveCallsTest() {

        List mock = Mockito.mock(List.class);

        when(mock.add("some arg"))
                .thenThrow(new RuntimeException())
                .thenReturn(true);

        //First call: throws runtime exception:
        Assertions.assertThrows(RuntimeException.class, () -> mock.add("some arg"));

        //Second call: returns true for second chained thenReturn method stubbing.
        Assertions.assertTrue(mock.add("some arg"));

        //Any consecutive call: prints true as well (last stubbing wins).
        Assertions.assertTrue(mock.add("some arg"));
        Assertions.assertTrue(mock.add("some arg"));
        Assertions.assertTrue(mock.add("some arg"));
        Assertions.assertTrue(mock.add("some arg"));
    }

    @Test
    void stubConsecutiveCallsAlternativelyTest() {

        List mock = Mockito.mock(List.class);

        when(mock.add("some arg"))
                .thenReturn(false, true, false, true, false);

        //First call: returns false
        Assertions.assertFalse(mock.add("some arg"));

        //Second call: returns true
        Assertions.assertTrue(mock.add("some arg"));

        Assertions.assertFalse(mock.add("some arg"));
        Assertions.assertTrue(mock.add("some arg"));
        Assertions.assertFalse(mock.add("some arg"));

        //Last Stub wins.
        Assertions.assertFalse(mock.add("some arg"));
        Assertions.assertFalse(mock.add("some arg"));
        Assertions.assertFalse(mock.add("some arg"));
        Assertions.assertFalse(mock.add("some arg"));
    }

}
