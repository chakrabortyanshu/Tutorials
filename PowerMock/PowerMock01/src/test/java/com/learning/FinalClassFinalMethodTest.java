package com.learning;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FinalServiceHolder.class)
public class FinalClassFinalMethodTest {

    @Test
    public void finalClassFinalMethodTest() {

        FinalServiceHolder finalServiceHolder = PowerMockito.mock(FinalServiceHolder.class);

        Object service = new Object();

        PowerMockito.doThrow(new ArrayIndexOutOfBoundsException("Mocked Exception")).when(finalServiceHolder).addService(service);

        //finalServiceHolder.addService(service);

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> finalServiceHolder.addService(service));
        Mockito.verify(finalServiceHolder).addService(service);

    }
}