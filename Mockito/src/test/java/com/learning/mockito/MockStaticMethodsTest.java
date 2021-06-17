package com.learning.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * When using the inline mock maker, it is possible to mock static method invocations within the current thread and a user-defined scope. This way, Mockito assures that concurrently and sequentially running tests do not interfere. To make sure a static mock remains temporary, it is recommended to define the scope within a try-with-resources construct. In the following example, the Foo type's static method would return foo unless mocked:
 *
 * https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/Mockito.html#0.2
 * https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/Mockito.html#39
 *
 * Just create in the classpath a file
 * /mockito-extensions/org.mockito.plugins.MockMaker containing the value mock-maker-inline.
 *
 * Gradle:
 * repositories {
 *    jcenter()
 *  }
 *  dependencies {
 *    testCompile "org.mockito:mockito-inline:+"
 *  }
 *
 */
public class MockStaticMethodsTest {

    @Test
    void name() {
        assertEquals("foo", Foo.method());

        /**
         * Just create in the classpath a file /mockito-extensions/org.mockito.plugins.MockMaker
         * containing the value mock-maker-inline.
         */
        try (MockedStatic mocked = mockStatic(Foo.class)) {
            mocked.when(Foo::method).thenReturn("bar");
            assertEquals("bar", Foo.method());
            mocked.verify(Foo::method);
        }
        assertEquals("foo", Foo.method());

        /**
         * Due to the defined scope of the static mock, it returns to its original behavior once the
         * scope is released. To define mock behavior and to verify static method invocations, use
         * the MockedStatic that is returned.
         */
    }

    /**
     * it is possible to generate mocks on constructor invocations within the current thread and a
     * user-defined scope. This way, Mockito assures that concurrently and sequentially running tests
     * do not interfere. To make sure a constructor mocks remain temporary, it is recommended to
     * define the scope within a try-with-resources construct. In the following example, the Foo type's
     * construction would generate a mock:
     */
    @Test
    void mockObjectConstruction() {
        Bar bar2 = new Bar();
        assertEquals("bar", bar2.method());
        try (MockedConstruction mocked = mockConstruction(Bar.class)) {
            Bar bar = new Bar();
            when(bar.method()).thenReturn("foo");
            assertEquals("foo", bar.method());
            verify(bar).method();
        }
        assertEquals("bar", bar2.method());

        /**
         * Due to the defined scope of the mocked construction, object construction returns
         * to its original behavior once the scope is released. To define mock behavior and
         * to verify static method invocations, use the MockedConstruction that is returned.
         */
    }
}

class Foo{

    public static String method(){
        return "foo";
    }
}

class Bar{
    public String method(){
        return "bar";
    }
}