package com.learning;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PartialMockClass.class)
public class SpyingWithPowerMockTest {

    @Test
    public void spyingWithPowerMockTest() throws Exception {
        PartialMockClass partialMockClass = PowerMockito.spy(new PartialMockClass());

        PowerMockito.when(partialMockClass, "privateMethodToMock").thenReturn("mockedPrivateMethod");

        String actualValue = partialMockClass.execute();

        Assertions.assertEquals("execute() mockedPrivateMethod", actualValue);

        PowerMockito.verifyPrivate(partialMockClass).invoke("privateMethodToMock");

    }
}
