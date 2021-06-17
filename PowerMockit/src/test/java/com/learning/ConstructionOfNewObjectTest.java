package com.learning;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(StaticClass.class)
public class ConstructionOfNewObjectTest {

    @Test
    public void mockingConstructionOfNewObjectTest() throws Exception {

        PowerMockito.whenNew(StaticClass.class).withNoArguments().thenThrow(new IOException("error message"));

        Assertions.assertThrows(IOException.class, () -> new StaticClass());

        PowerMockito.verifyNew(StaticClass.class).withNoArguments();

        //Mockito matchers are may still applied to a PowerMock mock:
        //Mockito.verify(mockObj).methodToMock(Mockito.anyInt());

    }
}
