package com.learning.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * https://github.com/mockito/mockito/wiki/What%27s-new-in-Mockito-2#mock-the-unmockable-opt-in-mocking-of-final-classesmethods
 * <p>
 * Mocking of final classes and methods is an incubating, opt-in feature.
 * It uses a combination of Java agent instrumentation and subclassing in
 * order to enable mockability of these types. As this works differently
 * to our current mechanism and this one has different limitations and as
 * we want to gather experience and user feedback, this feature had to be
 * explicitly activated to be available ; it can be done via the mockito
 * extension mechanism by creating the file
 * src/test/resources/mockito-extensions/org.mockito.plugins.MockMaker
 * containing a single line:
 * <p>
 * mock-maker-inline
 * <p>
 * In subsequent milestones, the team will bring a programmatic way of using
 * this feature. We will identify and provide support for all unmockable
 * scenarios. Stay tuned and please let us know what you think of this feature!
 */
class FinalClassTest {

    @Test
    void testingFinalMethod() {
        FinalClass concrete = new FinalClass();
        FinalClass mocked = Mockito.mock(FinalClass.class);

        BDDMockito.given(mocked.finalMethod()).willReturn("not anymore");

        Assertions.assertEquals("not anymore", mocked.finalMethod());
        Assertions.assertEquals("FinalString", concrete.finalMethod());
    }
}