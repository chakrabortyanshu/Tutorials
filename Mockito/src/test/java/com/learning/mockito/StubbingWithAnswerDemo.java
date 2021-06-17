package com.learning.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class StubbingWithAnswerDemo {

    @Test
    void stubbingWithAnswerDemoTest() {

        List mock = mock(List.class);

        when(mock.add(anyString())).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        Object[] args = invocation.getArguments();
                        Object mock = invocation.getMock();
                        System.out.println("called with arguments: " + Arrays.toString(args));
                        return false;
                    }
                });

        //Following prints "called with arguments: [foo]"
        Assertions.assertFalse(mock.add("foo"));

        Mockito.verify(mock).add("foo");

    }

    /**
     *
     * You can create a mock with specified strategy for its return values. It's quite an
     * advanced feature and typically you don't need it to write decent tests. However,
     * it can be helpful for working with legacy systems.
     * It is the default answer so it will be used only when you don't stub the method call.
     *
     *
     *    Foo mock = mock(Foo.class, Mockito.RETURNS_SMART_NULLS);
     *    Foo mockTwo = mock(Foo.class, new YourOwnAnswer());
     *
     *
     * Optional Answer to be used with mock(Class, Answer).
     * Answer can be used to define the return values of unstubbed invocations.
     *
     * This implementation can be helpful when working with legacy code. Unstubbed methods
     * often return null. If your code uses the object returned by an unstubbed call you get
     * a NullPointerException. This implementation of Answer returns SmartNull instead of
     * null. SmartNull gives nicer exception message than NPE because it points out the line
     * where unstubbed method was called. You just click on the stack trace.
     *
     * ReturnsSmartNulls first tries to return ordinary values (zeros, empty collections,
     * empty string, etc.) then it tries to return SmartNull. If the return type is final
     * then plain null is returned.
     *
     * ReturnsSmartNulls will be probably the default return values strategy in Mockito 4.0.0
     */
    @Test
    void stubbingWithAnswerSmartReturnsDemoTest() {

        Person mock = mock(Person.class, RETURNS_SMART_NULLS);
        //Person mock = mock(Person.class);

        //calling unstubbed method here:
        String name = mock.getName();

        //using object returned by unstubbed call:
        System.out.println(name.length());

        //Above doesn't yield NullPointerException this time!
        //Instead, SmartNullPointerException is thrown.
        //Exception's cause links to unstubbed mock.getName() - just click on the stack trace.

    }

    


}
