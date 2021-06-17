package com.learning.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DMTest {

    // detect unused stubs
    //@Rule
    //public MockitoRule mrule = MockitoJUnit.rule();

    // don't warn user about misusage, old behaviour
    //@Rule public MockitoRule mrule = MockitoJUnit.rule().silent();

    @Test
    void testInterface() {
        DM dm = mock(DM.class);

        given(dm.contract()).willReturn(2);
        given(dm.default_contract()).willCallRealMethod();

        Assertions.assertEquals(dm.default_contract(), 3);
    }
}