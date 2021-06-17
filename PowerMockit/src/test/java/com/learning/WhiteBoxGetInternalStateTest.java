package com.learning;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.powermock.reflect.Whitebox;

import java.util.Set;

import static org.junit.Assert.*;

public class WhiteBoxGetInternalStateTest {

    @Test
    public void testAddService() throws Exception {
        ServiceHolder tested = new ServiceHolder();
        final Object service = new Object();

        tested.addService(service);
        // This is how you get the private
        // services set using PowerMock
        Set services = (Set)
                Whitebox.getInternalState(tested, Set.class);
        Assertions.assertEquals(1, services.size());
        Assertions.assertSame(service, services.iterator().next());
    }

}