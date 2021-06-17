package com.learning.mockito.demo;

public class VerificationCollectorDemo {
    /**
     * https://github.com/mockito/mockito/wiki/What%27s-new-in-Mockito-2
     *
     * Lazy verification is possible with new incubating VerificationCollector Rule:
     *
     * @Rule
     * public VerificationCollector collector = MockitoJUnit.collector();
     * In a test method, all verifications are collected and reported at the end (if there are any failures):
     *
     * IMethods methods = mock(IMethods.class);
     * // Both methods are not called, but will be reported at once
     * verify(methods).simpleMethod();
     * verify(methods).byteReturningMethod();
     * The default non-lazy way would stop at the verification of simpleMethod and not report the missing verification of byteReturningMethod.
     */
}
