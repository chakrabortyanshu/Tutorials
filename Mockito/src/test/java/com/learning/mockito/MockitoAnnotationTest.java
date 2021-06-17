package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

/**
 * https://javadoc.io/doc/org.mockito/mockito-junit-jupiter/latest/org/mockito/junit/jupiter/MockitoExtension.html
 *
 * https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/junit/MockitoRule.html
 * https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/junit/MockitoJUnitRunner.html
 *
 */
@ExtendWith(MockitoExtension.class)
public class MockitoAnnotationTest {

    @Mock
    private List listMock;

    @Test
    void mockAnnotationTest() {

        Mockito.when(listMock.add(anyString())).thenReturn(true);

        listMock.add("abcd");

        Mockito.verify(listMock).add("abcd");

    }
}
