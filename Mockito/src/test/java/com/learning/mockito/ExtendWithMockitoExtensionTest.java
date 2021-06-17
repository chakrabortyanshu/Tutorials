package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

/**
 * Extension that initializes mocks and handles strict stubbings.
 */
@ExtendWith(MockitoExtension.class)
public class ExtendWithMockitoExtensionTest {

    @Mock
    private List<String> lists;

    @Test
    void shouldDoSomething() {
        lists.add("1001");
    }

    @Test
    public void hasLocalMockInThisTest(@Mock List<Integer> localList) {
        localList.add(100);
        lists.add("100");
    }
}

