package com.learning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@PrepareForTest(ChildClass.class)
@RunWith(PowerMockRunner.class)
public class SuppressSuperMethodCallTest {

    @Test
    public void onlyPrintChildClassTest() {
        // Setup - Both line works together.
        //ChildClass classUnderTest = PowerMockito.spy(new ChildClass());
        ChildClass classUnderTest = new ChildClass();

        PowerMockito.suppress(PowerMockito.methods(SuperClass.class, "foo"));

        // Test
        classUnderTest.foo();
    }


    @Test
    public void onlyPrintChildClassTestWithSecondAttempt() {
        ChildClass tested = Mockito.spy(new ChildClass());
        Mockito.doNothing().when((SuperClass) tested).foo();
        tested.foo();
    }
}
