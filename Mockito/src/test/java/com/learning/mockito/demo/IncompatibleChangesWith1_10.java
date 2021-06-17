package com.learning.mockito.demo;

public class IncompatibleChangesWith1_10 {
    /**
     * https://github.com/mockito/mockito/wiki/What%27s-new-in-Mockito-2#incompatible-changes-with-110
     *
     * We tried to minimize the amount of incompatible changes. In order to evolve the library, provide better testing experience, clean up old deprecated code, given the huge adoption of Java 8 and open up for brand new exciting features we need to release a new major version. Incompatible changes like the new anyX() matcher behaviour or detection of unused stubs should actually help with writing cleaner tests!
     *
     * Mockito 2 now requires Java 6 as a minimum.
     *
     * Mockito does not produce the mockito-all artifact anymore ; this one was primarily aimed at ant users, and contained other dependencies. We felt it was time to move on and remove such artifacts as they cause problems in dependency management system like maven or gradle.
     *
     * The Mockito class does not inherit from Matchers class anymore. Instead Mockito and Matchers classes both inherit from ArgumentMatchers. Also Matchers is now deprecated.
     *
     * Matchers.argThat method family had a direct dependency on hamcrest, in order to allow matchers to work without Hamcrest we had to move these API to MockitoHamcrest.
     *
     * Change these
     *
     * Matchers.argThat()
     * Matchers.charThat()
     * // similar hamcrest method
     * to
     *
     * MockitoHamcrest.argThat()
     * MockitoHamcrest.charThat()
     * // similar hamcrest method
     * InvocationOnMock.getArgumentAt(int,Class) was replaced with InvocationOnMock.getArgument(int), it simplifies the implementation of custom answers.
     *
     * Change these
     *
     * public Object answer(InvocationOnMock invocation) {
     *   MyClass first = getArgumentAt(0,MyClass.class);
     *   ...
     * }
     * to
     *
     * public Object answer(InvocationOnMock invocation) {
     *   MyClass first = getArgument(0);
     *   ...
     * }
     * anyX() and any(SomeType.class) matchers now reject nulls and check type. This has been long waited feature, making Mockito syntax more intuitive. (In the following list T represents some type like Integer, List)
     *
     * <T> T any() will matches anything including null
     * T anyT() / <T> any(Class<T>) are aliases of <T> isA(T), will matches non-null object of given type. e.g. :
     * int anyInt() won't match null (for Integers) anymore
     * String anyString() won't match null (for Strings) anymore, and will check the object is indeed a String
     * Map<K, V> anyMap() won't match null (for Maps) anymore, and will check the object is indeed a Map
     * <T> List<T> anyListOf(Class<T>) will match non-null List, and will check the object is indeed a List
     * Collection type matching are shallow, it means matchers won't verify the type of contained elements, instead it relies on the compiler to warn users on generic misuses.
     *
     * Some public APIs have been tweaked with varargs, this shouldn't affect a lot of users. However for library developers that rely on binary compatibility, it may require a little work.
     *
     * BDDMockito.given(mock.invocation()).willThrow(Throwable) // mockito 1.x
     * BDDMockito.given(mock.invocation()).willThrow(Throwable...) // mockito 2.x
     * MockMaker now declares a new method isTypeMockable(Class<?>) which must be implemented. Framework integrators were requesting this feature.
     *
     * Deprecated symbols in 1.x have been removed, like
     *
     * MockitoAnnotations.Mock
     * Other internal classes that were previously public like : org.mockito.internal.stubbing.defaultanswers.Answers
     * Various internal changes that may impact library developers
     *
     * Package change
     * Some classes are now static
     * For more, take a look at the list of incompatible changes{https://github.com/mockito/mockito/issues?utf8=%E2%9C%93&q=label%3A%221.*%20incompatible%22%20} on GH.
     *
     */

}
