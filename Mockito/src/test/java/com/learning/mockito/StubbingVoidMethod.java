package com.learning.mockito;

import javafx.beans.binding.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


/**
 * Stubbing void methods requires a different approach from when(Object) because the compiler does not like void methods inside brackets...
 */
public class StubbingVoidMethod {

    @Test
    void doReturnTest() {
        List mock = mock(List.class);

        doReturn(Boolean.FALSE).when(mock).add(anyString());

        Assertions.assertFalse(mock.add("abcd"));

        verify(mock).add("abcd");

        //When spying real objects and calling real methods on a spy brings side effects

        List list = new LinkedList();
        List spy = spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        //when(spy.get(0)).thenReturn("foo");

        //You have to use doReturn() for stubbing:
        doReturn("foo").when(spy).get(0);

        Assertions.assertEquals("foo", spy.get(0));
        verify(spy).get(0);

        //Overriding a previous exception-stubbing:

        when(mock.size()).thenThrow(new RuntimeException());

        //Impossible: the exception-stubbed foo() method is called so RuntimeException is thrown.
        //when(mock.size()).thenReturn(10);

        //You have to use doReturn() for stubbing:
        doReturn(20).when(mock).size();

        Assertions.assertEquals(20, mock.size());


    }

    @Test
    void doThrowTest() {

        List mockedList = mock(List.class);

        //doThrow(new RuntimeException()).when(mock).someVoidMethod();
        //doThrow(RuntimeException.class, BigFailure.class).when(mock).someVoidMethod();
        doThrow(new RuntimeException()).when(mockedList).clear();

        //following throws RuntimeException:
        Assertions.assertThrows(RuntimeException.class, () -> mockedList.clear());

        verify(mockedList).clear();
    }

    @Test
    void doAnswerTest() {

        List mock = Mockito.mock(List.class);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return null;
            }})
                .when(mock).get(anyInt());

        Assertions.assertNull(mock.get(0));

        verify(mock).get(0);
    }

    @Test
    @DisplayName("Stubbing consecutive calls on a void method")
    void doNothingStubbingConsecutiveCallsOnVoidMethodTest() {

        Person mock = Mockito.mock(Person.class);

        doNothing().
                doThrow(new RuntimeException())
                .when(mock).setName("ABCD");

        //does nothing the first time:
        mock.setName("ABCD");

        //throws RuntimeException the next time:
        Assertions.assertThrows(RuntimeException.class, () -> mock.setName("ABCD"));

        verify(mock, times(2)).setName("ABCD");
    }

    @Test
    @DisplayName("When you spy real objects and you want the void method to do nothing")
    void spyRealObjectVoidMethodToDoNothingTest() {
        List list = new LinkedList();
        List spy = spy(list);

        //let's make clear() do nothing
        doNothing().when(spy).clear();

        spy.add("one");

        //clear() does nothing, so the list still contains "one"
        spy.clear();

        Assertions.assertEquals(1, spy.size());

    }

    @Test
    void doCallRealMethodTest() {
        Person mock = mock(Person.class);
        doCallRealMethod().when(mock).setName(anyString());

        // this will call the real implementation of Foo.someVoidMethod()
        mock.setName("EJGH");

    }
}
