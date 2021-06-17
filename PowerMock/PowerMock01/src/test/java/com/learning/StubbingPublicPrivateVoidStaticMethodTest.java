package com.learning;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ServiceHolder.class)
public class StubbingPublicPrivateVoidStaticMethodTest {

    @Test
    public void stubbingVoidStaticMethodTest() throws Exception {

        PowerMockito.mockStatic(ServiceHolder.class);

        //Same method works for private as well.
        PowerMockito.doThrow(new ArrayStoreException("Mock error")).when(ServiceHolder.class,"getString");

        Assertions.assertThrows(ArrayStoreException.class, () ->ServiceHolder.getString());

        PowerMockito.verifyStatic(ServiceHolder.class, atLeastOnce());
        ServiceHolder.getString();
    }
}
