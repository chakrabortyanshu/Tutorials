package com.learning.mockito;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonTest {

    @Mock
    private NumberGenerator numberGenerator;

    @Mock
    private Person person;

    @BeforeEach
    void initialize(TestInfo testInfo) {
        when(person.getName()).thenReturn(testInfo.getDisplayName());
        when(numberGenerator.next()).thenReturn(42);
    }

    @Test
    @DisplayName("firstTestWithInjectedMock(Person)")
    void firstTestWithInjectedMock() {
        assertEquals("firstTestWithInjectedMock(Person)", person.getName());
        assertEquals(42, numberGenerator.next());
    }
}