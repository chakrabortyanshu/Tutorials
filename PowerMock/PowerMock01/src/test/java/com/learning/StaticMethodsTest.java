package com.learning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(IdGenerator.class)
public class StaticMethodsTest {

    @Test
    public void demoStaticMethodMocking() {
        PowerMockito.mockStatic(IdGenerator.class);
        /*
         * Setup the expectation using the standard Mockito syntax,
         * generateNewId() will now return 2 everytime it's invoked
         * in this test.
         */
        Mockito.when(IdGenerator.generateNewId()).thenReturn(2L);

        new IDGeneratorSample().methodToTest();

        // Optionally verify that the static method was actually called
        PowerMockito.verifyStatic(IdGenerator.class);
        IdGenerator.generateNewId();
    }
}