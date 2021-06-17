package com.learning;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({StaticClass.class})
public class StaticMethodTest {

    @Test
    public void powermockitoTest() {
        PowerMockito.mockStatic(StaticClass.class);
        //Start using Mockito
        Mockito.when(StaticClass.staticMethod()).thenReturn("Mocked Value");
        Mockito.when(StaticClass.staticIntMethod()).thenReturn(250);

        Assertions.assertEquals("Mocked Value", StaticClass.staticMethod());
        Assertions.assertEquals(250, StaticClass.staticIntMethod());

        //Verify Static
        //Verification of a static method is done in two steps.
        //First call PowerMockito.verifyStatic(Static.class) to start verifying behavior and then
        //Call the static method of the Static.class to verify.
        PowerMockito.verifyStatic(StaticClass.class);
        StaticClass.staticMethod();

        //Important: You need to call verifyStatic(Static.class) per method verification.
        PowerMockito.verifyStatic(StaticClass.class);
        StaticClass.staticIntMethod();

    }

}