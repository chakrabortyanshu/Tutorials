/*
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package examples.tutorial.domainmocking.impl;

import domain.BusinessMessages;
import domain.Person;
import domain.SampleServiceException;
import domainmocking.EventService;
import domainmocking.PersonService;
import impl.SampleServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * The purpose of this test is to get 100% coverage of the
 * {@link SampleServiceImpl} class without any code changes to that class. To
 * achieve this you need learn how to mock instantiation of domain objects.
 * <p>
 * While doing this tutorial please refer to the documentation on how to mock
 * construction of new objects at the PowerMock web site.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SampleServiceImpl.class)
public class SampleServiceImplTest {

    private SampleServiceImpl tested;
    private PersonService personServiceMock;
    private EventService eventServiceMock;
    private String firstName = "Firstname";
    private String lastName = "Lastname";

    @Before
    public void setUp() {
        personServiceMock = PowerMockito.mock(PersonService.class);
        eventServiceMock = PowerMockito.mock(EventService.class);
        tested = new SampleServiceImpl(personServiceMock, eventServiceMock);
    }

    @Test
    public void testCreatePerson() throws Exception {

        BusinessMessages businessMessagesMock = PowerMockito.mock(BusinessMessages.class);
        Person person = PowerMockito.mock(Person.class);

        PowerMockito.whenNew( BusinessMessages.class).withNoArguments().thenReturn(businessMessagesMock);
        PowerMockito.whenNew(Person.class).withArguments(firstName, lastName).thenReturn(person);

        Mockito.when(businessMessagesMock.hasErrors()).thenReturn(Boolean.FALSE);
        Mockito.doNothing().when(personServiceMock).create(person, businessMessagesMock);

        Assertions.assertTrue(tested.createPerson(firstName, lastName));

        Mockito.verify(businessMessagesMock).hasErrors();
        PowerMockito.verifyNew(BusinessMessages.class).withNoArguments();

        PowerMockito.verifyNew(Person.class).withArguments(firstName,lastName);
    }

    @Test
    public void testCreatePerson_error() throws Exception {

        BusinessMessages businessMessagesMock = PowerMockito.mock(BusinessMessages.class);
        Person person = PowerMockito.mock(Person.class);

        PowerMockito.whenNew( BusinessMessages.class).withNoArguments().thenReturn(businessMessagesMock);
        PowerMockito.whenNew(Person.class).withArguments(firstName, lastName).thenReturn(person);

        Mockito.when(businessMessagesMock.hasErrors()).thenReturn(Boolean.TRUE);
        Mockito.doNothing().when(personServiceMock).create(person, businessMessagesMock);

        Assertions.assertFalse(tested.createPerson(firstName, lastName));

        Mockito.verify(businessMessagesMock).hasErrors();
        PowerMockito.verifyNew(BusinessMessages.class).withNoArguments();

        PowerMockito.verifyNew(Person.class).withArguments(firstName,lastName);

    }

    @Test(expected = SampleServiceException.class)
    public void testCreatePerson_illegalName() throws Exception {

        Person person = PowerMockito.mock(Person.class);
        BusinessMessages businessMessagesMock = PowerMockito.mock(BusinessMessages.class);

        PowerMockito.whenNew(BusinessMessages.class).withNoArguments().thenReturn(businessMessagesMock);
        Mockito.when(businessMessagesMock.hasErrors()).thenReturn(Boolean.FALSE);

        Assertions.assertTrue(tested.createPerson(null,null));

        PowerMockito.verifyNew(BusinessMessages.class).withNoArguments();
        Mockito.verify(businessMessagesMock).hasErrors();

    }

    @After
    public void tearDown() {
        personServiceMock = null;
        eventServiceMock = null;
        tested = null;
    }
}
