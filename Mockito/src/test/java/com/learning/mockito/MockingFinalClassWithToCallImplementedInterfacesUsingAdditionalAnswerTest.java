package com.learning.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.AdditionalAnswers.delegatesTo;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Useful for spies or partial mocks of objects that are difficult to mock or spy using the usual spy API. Since Mockito 1.10.11, the delegate may or may not be of the same type as the mock. If the type is different, a matching method needs to be found on delegate type otherwise an exception is thrown. Possible use cases for this feature:
 *
 * Final classes but with an interface
 * Already custom proxied object
 * Special objects with a finalize method, i.e. to avoid executing it 2 times
 * The difference with the regular spy:
 *
 * The regular spy (spy(Object)) contains all state from the spied instance and the methods are invoked on the spy. The spied instance is only used at mock creation to copy the state from. If you call a method on a regular spy and it internally calls other methods on this spy, those calls are remembered for verifications, and they can be effectively stubbed.
 * The mock that delegates simply delegates all methods to the delegate. The delegate is used all the time as methods are delegated onto it. If you call a method on a mock that delegates and it internally calls other methods on this mock, those calls are not remembered for verifications, stubbing does not have effect on them, too. Mock that delegates is less powerful than the regular spy but it is useful when the regular spy cannot be created.
 * See more information in docs for AdditionalAnswers.delegatesTo(Object).
 *
 */
public class MockingFinalClassWithToCallImplementedInterfacesUsingAdditionalAnswerTest {

    @Test
    void additionalAnswerTest() {
        DontYouDareToMockMe awesomeList = new DontYouDareToMockMe();
        Abcd mock = mock(Abcd.class, delegatesTo(awesomeList));

        /**
         *
         * This feature suffers from the same drawback as the spy. The mock
         * will call the delegate if you use regular when().then() stubbing
         * style. Since the real implementation is called this might have some
         * side effects. Therefore you should use
         * the doReturn|Throw|Answer|CallRealMethod stubbing style. Example:
         */
        Abcd listWithDelegate = mock(Abcd.class, AdditionalAnswers.delegatesTo(awesomeList));

        //Impossible: real method is called so listWithDelegate.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        //Mockito.when(listWithDelegate.getValue()).thenReturn(156);

        //You have to use doReturn() for stubbing
        doReturn(159).when(listWithDelegate).getValue();

        Assertions.assertEquals(159, listWithDelegate.getValue());

        Mockito.verify(listWithDelegate).getValue();

    }
}

interface  Abcd{ public int getValue();}

final class DontYouDareToMockMe implements Abcd {
    @Override
    public int getValue() {
        return 0;
    }
}



