package com.learning.mockito;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 *
 * Smart Mockito users hardly use this feature because they know it could be a sign of poor tests.
 * Normally, you don't need to reset your mocks, just create new mocks for each test method.
 * Instead of reset() please consider writing simple, small and focused test methods over lengthy,
 * over-specified tests. First potential code smell is reset() in the middle of the test method.
 * This probably means you're testing too much. Follow the whisper of your test methods:
 * "Please keep us small & focused on single behavior". There are several threads about it on mockito
 * mailing list.
 *
 * The only reason we added reset() method is to make it possible to work with container-injected mocks.
 * For more information see FAQ (here).
 *
 * Don't harm yourself. reset() in the middle of the test method is a code smell (you're probably testing too much).
 *
 */
public class ResettingMockingTest {

    @Test
    void resetMockingTest() {
        List mock = mock(List.class);
        when(mock.size()).thenReturn(10);
        mock.add(1);

        reset(mock);
        //at this point the mock forgot any interactions & stubbing
    }


}
