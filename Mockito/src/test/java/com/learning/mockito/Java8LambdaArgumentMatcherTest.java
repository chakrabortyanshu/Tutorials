package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * You can use Java 8 lambda expressions with ArgumentMatcher to reduce the
 * dependency on ArgumentCaptor. If you need to verify that the input to a
 * function call on a mock was correct, then you would normally use the
 * ArgumentCaptor to find the operands used and then do subsequent assertions
 * on them. While for complex examples this can be useful, it's also long-winded.
 *
 * Writing a lambda to express the match is quite easy. The argument to your function,
 * when used in conjunction with argThat, will be passed to the ArgumentMatcher as a
 * strongly typed object, so it is possible to do anything with it.
 *
 * Examples:
 */
public class Java8LambdaArgumentMatcherTest {

    @Test
    void testLambdaArgumentMatcher() {

        /*
        // verify a list only had strings of a certain length added to it
        // note - this will only compile under Java 8
        verify(list, times(2)).add(argThat(string -> string.length() < 5));

        // Java 7 equivalent - not as neat
        verify(list, times(2)).add(argThat(new ArgumentMatcher(){
            public boolean matches(String arg) {
                return arg.length() < 5;
            }
        }));

        // more complex Java 8 example - where you can specify complex verification behaviour functionally
        verify(target, times(1)).receiveComplexObject(argThat(obj -> obj.getSubObject().get(0).equals("expected")));

        // this can also be used when defining the behaviour of a mock under different inputs
        // in this case if the input list was fewer than 3 items the mock returns null
        when(mock.someMethod(argThat(list -> list.size()<3))).thenReturn(null);

         */
    }
}
