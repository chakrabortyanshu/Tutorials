package com.learning;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.util.LinkedList;
import java.util.List;


public class SpyingStaticAndPrivateMethodTest {

    @Test
    void spyStaticClassObjectTest() {
        List list = new LinkedList();
        List spy = PowerMockito.spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        //Mockito.when(spy.get(10)).thenReturn("foo");

        Mockito.doReturn("foo").when(spy).get(10);

        Assertions.assertEquals("foo", spy.get(10));

        Mockito.verify(spy).get(10);
        Mockito.verify(spy,Mockito.times(1)).get(10);

    }

}
