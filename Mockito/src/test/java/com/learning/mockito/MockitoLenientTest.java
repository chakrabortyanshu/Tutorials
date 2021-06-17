package com.learning.mockito;

public class MockitoLenientTest {

    /**
     * 46. New Mockito.lenient() and MockSettings.lenient() methods (Since 2.20.0)
     * Strict stubbing feature is available since early Mockito 2. It is very useful because it drives cleaner tests and improved productivity. Strict stubbing reports unnecessary stubs, detects stubbing argument mismatch and makes the tests more DRY (Strictness.STRICT_STUBS). This comes with a trade-off: in some cases, you may get false negatives from strict stubbing. To remedy those scenarios you can now configure specific stubbing to be lenient, while all the other stubbings and mocks use strict stubbing:
     *
     *    lenient().when(mock.foo()).thenReturn("ok");
     *
     * If you want all the stubbings on a given mock to be lenient, you can configure the mock accordingly:
     *
     *    Foo mock = Mockito.mock(Foo.class, withSettings().lenient());
     *
     * For more information refer to lenient(). Let us know how do you find the new feature by opening a GitHub issue to discuss!
     *
     * https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/quality/Strictness.html#STRICT_STUBS
     * https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/Mockito.html#lenient--
     *
     */
}
