package com.learning.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SpyOnRealObjectTest {

    @Test
    void spyOnRealObjectTest() {
        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        Assertions.assertEquals(100,spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
        verify(spy).get(0);
        verify(spy).size();
    }

    @Test
    void spyRealObjectWithDoReturnTest() {
        /**
         * Sometimes it's impossible or impractical to use when(Object) for stubbing spies.
         * Therefore when using spies please consider doReturn|Answer|Throw() family of methods
         * for stubbing.
         */

        List list = new LinkedList();
        List spy = spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        //when(spy.get(0)).thenReturn("foo");

        //You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);

        /**
         *
         * Mockito *does not* delegate calls to the passed real instance, instead it actually creates
         * a copy of it. So if you keep the real instance and interact with it, don't expect the spied
         * to be aware of those interaction and their effect on real instance state. The corollary is
         * that when an *unstubbed* method is called *on the spy* but *not on the real instance*, you
         * won't see any effects on the real instance.
         * Watch out for final methods. Mockito doesn't mock final methods so the bottom line is: when
         * you spy on real objects + you try to stub a final method = trouble. Also you won't be able
         * to verify those method as well.
         *
         */
    }


}
