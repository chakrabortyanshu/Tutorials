package com.learning;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PartialMockClass.class)
public class PartialMockingOfPrivateMethodTest {

    @Test
    public void privatePartialMockingWithPowerMockTest() throws Exception {

        PartialMockClass classUnderTest = PowerMockito.spy(new PartialMockClass());

        // use PowerMockito to set up your expectation
        //Both styles of mocking works here.
        //PowerMockito.doReturn("MockedPrivateMethod").when(classUnderTest, "privateMethodToMock");
        PowerMockito.when(classUnderTest, "privateMethodToMock").thenReturn("MockedPrivateMethod");

        // execute your test
        String actual = classUnderTest.execute();

        Assertions.assertEquals("execute() MockedPrivateMethod", actual);

        // Use PowerMockito.verify() to verify result
        PowerMockito.verifyPrivate(classUnderTest, Mockito.times(1)).invoke("privateMethodToMock");

    }
}
