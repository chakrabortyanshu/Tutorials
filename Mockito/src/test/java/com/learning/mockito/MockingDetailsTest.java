package com.learning.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.MockingDetails;
import org.mockito.Mockito;

import static org.mockito.Mockito.mockingDetails;

/**
 *
 * Mockito offers API to inspect the details of a mock object.
 * This API is useful for advanced users and mocking framework
 * integrators.
 *
 * https://javadoc.io/static/org.mockito/mockito-core/3.5.11/org/mockito/MockingDetails.html
 * 
 */
public class MockingDetailsTest {

    @Test
    void mockingDetailsTest() {

        Person someObject = Mockito.mock(Person.class);
        Person mock = Mockito.mock(Person.class);

        //To identify whether a particular object is a mock or a spy:
        mockingDetails(someObject).isMock();
        mockingDetails(someObject).isSpy();

        //Getting details like type to mock or default answer:
        MockingDetails details = mockingDetails(mock);
        details.getMockCreationSettings().getTypeToMock();
        details.getMockCreationSettings().getDefaultAnswer();

        //Getting invocations and stubbings of the mock:
        MockingDetails mockingDetails = mockingDetails(mock);
        mockingDetails.getInvocations();
        mockingDetails.getStubbings();

        //Printing all interactions (including stubbing, unused stubs)
        System.out.println(mockingDetails(mock).printInvocations());

    }
}
