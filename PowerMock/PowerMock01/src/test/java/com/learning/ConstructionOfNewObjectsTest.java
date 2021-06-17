package com.learning;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;


@RunWith(PowerMockRunner.class)
@PrepareForTest(DirectoryStructure.class)
public class ConstructionOfNewObjectsTest {

    @Test
    public void createDirectoryStructureWhenPathDoesntExist() throws Exception {

        final String directoryPath = "mocked path";

        File directoryMock = PowerMockito.mock(File.class);

        // This is how you tell PowerMockito to mock construction of a new File.
        PowerMockito.whenNew(File.class).withArguments(directoryPath).thenReturn(directoryMock);

        // Standard expectations
        Mockito.when(directoryMock.exists()).thenReturn(false);
        Mockito.when(directoryMock.mkdirs()).thenReturn(true);

        Assertions.assertTrue(new DirectoryStructure().create(directoryPath));

        // Optionally verify that a new File was "created".
        PowerMockito.verifyNew(File.class).withArguments(directoryPath);
    }
}