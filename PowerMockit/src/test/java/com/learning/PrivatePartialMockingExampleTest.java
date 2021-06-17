package com.learning;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;


@RunWith(PowerMockRunner.class)
@PrepareForTest(PrivatePartialMockingExample.class)
public class PrivatePartialMockingExampleTest {

    @Test
    public void demoPrivateMethodMocking() throws Exception {
        final String expected = "TEST VALUE";
        final String nameOfMethodToMock = "methodToMock";
        final String input = "input";

        PrivatePartialMockingExample underTest = PowerMockito.spy(new PrivatePartialMockingExample());

        /*
         * Setup the expectation to the private method using the method name
         */
        PowerMockito.when(underTest, nameOfMethodToMock, input).thenReturn(expected);

        Assertions.assertEquals(expected, underTest.methodToTest());

        // Optionally verify that the private method was actually called
        PowerMockito.verifyPrivate(underTest).invoke(nameOfMethodToMock, input);
    }

    @Test
    public void demoPrivateMethodMocking2() throws Exception {
        final String expected = "TEST VALUE";
        final String input = "input";

        /*
         * We get the method to mock by specifying the class where the
         * method is defined as well as its parameter types.
         */
        final Method methodToMock = PowerMockito.method(PrivatePartialMockingExample.class, String.class);

        PrivatePartialMockingExample underTest = PowerMockito.spy(new PrivatePartialMockingExample());

        // Notice how we pass the actual method instead of the name
        PowerMockito.when(underTest, methodToMock).withArguments(input).thenReturn(expected);

        Assertions.assertEquals(expected, underTest.methodToTest());

        // Optionally verify that the private method was actually called
        PowerMockito.verifyPrivate(underTest).invoke(methodToMock).withArguments(input);
    }
}