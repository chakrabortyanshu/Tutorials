package com.learning.mockito;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class SpyMockitoAnnotationTest {
    @Spy
    Person person = new Person();
    //Instance for spying is created by mockito via reflection (only default constructors supported):
    // Same as doing
    // Person Person = Mockito.spy(Person.class);

    private AutoCloseable closeable;

    @BeforeEach
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void spyAnnotationTest() {

        Mockito.when(person.getName()).thenReturn("ZYXW");

        person.setName("ABCD");

        Assertions.assertEquals("ZYXW", person.getName());

        Mockito.verify(person).setName("ABCD");
        Mockito.verify(person).getName();
    }

    @AfterEach
    public void release() throws Exception {
        closeable.close();
    }

}
