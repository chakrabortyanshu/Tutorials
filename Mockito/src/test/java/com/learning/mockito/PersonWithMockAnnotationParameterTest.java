package com.learning.mockito;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * https://github.com/mockito/mockito/wiki/What%27s-new-in-Mockito-2
 * <p>
 * Got this example from above url but it doesn't work.
 */
@ExtendWith(MockitoExtension.class)
class PersonWithMockAnnotationParameterTest {

/**

 @Mock private NumberGenerator numberGenerator;


 @BeforeEach void initialize(@Mock Person person, TestInfo testInfo) {
 when(person.getName()).thenReturn(testInfo.getDisplayName());
 when(numberGenerator.next()).thenReturn(42);
 }

 @Test
 @DisplayName("firstTestWithInjectedMock(Person)") void firstTestWithInjectedMock(@Mock Person person) {
 assertEquals("firstTestWithInjectedMock(Person)", person.getName());
 assertEquals(42, numberGenerator.next());
 }
 */
}