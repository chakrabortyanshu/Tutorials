package com.learning;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import org.powermock.api.support.membermodification.MemberMatcher;

public class SupressMoreExampleTest {
    /**
     * https://blog.jayway.com/2009/10/28/untestable-code-with-mockito-and-powermock/
     *
     * Example #4 - Modify code structure or change invocations
     * A new noteworthy feature of the 1.3 release is the new "member modification" API.
     * Using this API you can suppress methods, fields or constructors as well stubbing
     * and replacing methods and fields.
     * <p>
     * Below you can see some examples of how to suppress members in a class:
     * <p>
     * // Suppress a constructor in class ClassWithConstructor
     * suppress(constructor(ClassWithConstructor.class));
     * // Suppress method "myMethod" in class MyClass
     * suppress(method(MyClass.class, "myMethod"));
     * // Suppress all methods in class MyClass
     * suppress(methodsDeclaredIn(MyClass.class));
     * // Suppress field with name "myField" in class MyClass
     * suppress(field(MyClass.class, "myField"));
     * You can also stub methods:
     * <p>
     * //Stub the "getObject" method in class SuppressMethod to always return expectedValue
     * stub(method(SuppressMethod.class, "getObject")).andReturn(expectedValue);
     * And replace method invocations:
     * <p>
     * // Duck-typing of static methods
     * replace(method(MyClass.class, "getData")).with(method(MyClass2.class, "getTestData"));
     * // Replace method invocation with an invocation handler to allow for proxy-based AOP
     * replace(method(MyClass.class, "getData")).with(new MyInvocationHandler());
     * <p>
     * All methods can be statically imported from org.powermock.api.support.membermodification.
     * MemberModifier and org.powermock.api.support.membermodification.MemberMatcher.
     * For real examples refer to MemberModificationExampleTest.java in subversion.
     */

    @Test
    public void suppressExampleTest() {
        // Suppress a constructor in class ClassWithConstructor
        PowerMockito.suppress(MemberMatcher.constructor(PartialMockClass.class)); //Class With Constructor.
        // Suppress method "myMethod" in class MyClass
        PowerMockito.suppress(MemberMatcher.method(PartialMockClass.class, "privateMethodToMock"));
        // Suppress all methods in class MyClass
        PowerMockito.suppress(MemberMatcher.methodsDeclaredIn(PartialMockClass.class));
        // Suppress field with name "myField" in class MyClass
        PowerMockito.suppress(MemberMatcher.field(FinalServiceHolder.class, "services"));
    }

    @Test
    public void stubMethodTest() {
        //You can also stub methods:

        //Stub the "getObject" method in class SuppressMethod to always return expectedValue
        //PowerMockito.stub(MemberMatcher.method(FinalServiceHolder.class, "privateMethodToMock")).andReturn("Mocked Value");
    }

    @Test
    public void replaceMethodInvocationTest() {
        // Duck-typing of static methods
        //PowerMockito.replace(MemberMatcher.method(FinalServiceHolder.class, "privateMethodToMock")).with(MemberMatcher.method(ChildClass.class, "foo"));
        // Replace method invocation with an invocation handler to allow for proxy-based AOP
        //PowerMockito.replace(MemberMatcher.method(FinalServiceHolder.class, "privateMethodToMock")).with(new MyInvocationHandler());
    }
}
