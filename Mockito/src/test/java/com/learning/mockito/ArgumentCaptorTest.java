package com.learning.mockito;

public class ArgumentCaptorTest {

    /**
     * 15. Capturing arguments for further assertions (Since 1.8.0)
     * Mockito verifies argument values in natural java style: by using an equals() method. This is also the recommended way of matching arguments because it makes tests clean & simple. In some situations though, it is helpful to assert on certain arguments after the actual verification. For example:
     *
     *    ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
     *    verify(mock).doSomething(argument.capture());
     *    assertEquals("John", argument.getValue().getName());
     *
     * Warning: it is recommended to use ArgumentCaptor with verification but not with stubbing. Using ArgumentCaptor with stubbing may decrease test readability because captor is created outside of assert (aka verify or 'then') block. Also it may reduce defect localization because if stubbed method was not called then no argument is captured.
     * In a way ArgumentCaptor is related to custom argument matchers (see javadoc for ArgumentMatcher class). Both techniques can be used for making sure certain arguments were passed to mocks. However, ArgumentCaptor may be a better fit if:
     *
     * custom argument matcher is not likely to be reused
     * you just need it to assert on argument values to complete verification
     * Custom argument matchers via ArgumentMatcher are usually better for stubbing.
     *
     */

}
