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
package examples.tutorial.partialmocking.service.impl;

import dao.ProviderDao;
import domain.DataProducer;
import domain.ServiceProducer;
import impl.ProviderServiceImpl;
import impl.ServiceArtifact;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import service.ProviderService;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * The purpose of this test is to get 100% coverage of the
 * {@link ProviderServiceImpl} class without any code changes to that class. To
 * achieve this you need learn how to create partial mocks, modify internal
 * state, invoke and expect private methods.
 * <p>
 * While doing this tutorial please refer to the documentation on how to expect
 * private methods and bypass encapsulation at the PowerMock web site.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ProviderServiceImpl.class)
public class ProviderServiceImplTest {
	
	private ProviderServiceImpl tested;
	private ProviderDao providerDaoMock;


	@Before
	public void setUp() {
		providerDaoMock = PowerMockito.mock(ProviderDao.class);
		tested = new ProviderServiceImpl();
		Whitebox.setInternalState(tested, "providerDao",providerDaoMock);
	}

	@After
	public void tearDown() {
		providerDaoMock = null;
		tested = null;
	}

	@Test
	public void testGetAllServiceProviders() throws Exception {

		Method privateMethod = PowerMockito.method(ProviderServiceImpl.class, "getAllServiceProducers", null);

		ProviderServiceImpl providerServiceSpy = PowerMockito.spy(tested);

		Set<ServiceProducer> serviceProducers = new HashSet<>();
		PowerMockito.doReturn(serviceProducers).when(providerServiceSpy, privateMethod).withNoArguments();

		Set<ServiceProducer> actual = providerServiceSpy.getAllServiceProviders();

		Assertions.assertSame(serviceProducers, actual);

		PowerMockito.verifyPrivate(providerServiceSpy).invoke(privateMethod).withNoArguments();

	}

	@Test
	public void testGetAllServiceProviders_noServiceProvidersFound() throws Exception {

		Method privateMethod = PowerMockito.method(ProviderServiceImpl.class, "getAllServiceProducers", null);

		ProviderServiceImpl providerServiceSpy = PowerMockito.spy(tested);

		PowerMockito.doReturn(null).when(providerServiceSpy, privateMethod).withNoArguments();

		Set<ServiceProducer> actual = providerServiceSpy.getAllServiceProviders();

		Set<Object> expected = Collections.emptySet();

		Assertions.assertEquals(expected, actual);

		PowerMockito.verifyPrivate(providerServiceSpy).invoke(privateMethod).withNoArguments();
	}

	@Test
	public void testServiceProvider_found() throws Exception {

		Method methodGetAllServiceProducers = PowerMockito.method(ProviderServiceImpl.class, "getAllServiceProducers");
		int number = 1;

		ServiceProducer serviceProducerMock = Mockito.mock(ServiceProducer.class);
		Mockito.when(serviceProducerMock.getId()).thenReturn(Integer.valueOf(number));

		ProviderServiceImpl providerServiceImplspy = PowerMockito.spy(tested);

		PowerMockito.doReturn(Sets.newSet(serviceProducerMock)).when(providerServiceImplspy,methodGetAllServiceProducers).withNoArguments();

		Assertions.assertSame(serviceProducerMock,providerServiceImplspy.getServiceProvider(number));

		PowerMockito.verifyPrivate(providerServiceImplspy).invoke(methodGetAllServiceProducers).withNoArguments();
		Mockito.verify(serviceProducerMock).getId();

	}

	@Test
	public void testServiceProvider_notFound() throws Exception {
		Method methodGetAllServiceProducers = PowerMockito.method(ProviderServiceImpl.class, "getAllServiceProducers");
		int number = 1;

		ServiceProducer serviceProducerMock = Mockito.mock(ServiceProducer.class);
		Mockito.when(serviceProducerMock.getId()).thenReturn(Integer.valueOf(number));

		ProviderServiceImpl providerServiceImplspy = PowerMockito.spy(tested);

		PowerMockito.doReturn(Sets.newSet(serviceProducerMock)).when(providerServiceImplspy,methodGetAllServiceProducers).withNoArguments();

		Assertions.assertNull(providerServiceImplspy.getServiceProvider(number+1));

		PowerMockito.verifyPrivate(providerServiceImplspy).invoke(methodGetAllServiceProducers).withNoArguments();
		Mockito.verify(serviceProducerMock).getId();

	}

	@Test
	public void getAllServiceProducers() throws Exception {

		DataProducer dataProducer = new DataProducer(0, "DataProducer");
		ServiceArtifact serviceArtifact = new ServiceArtifact(1, "plainObject", dataProducer);

		Mockito.when(providerDaoMock.getAllServiceProducers()).thenReturn(Sets.newSet(serviceArtifact));

		Set<ServiceProducer> serviceProducers = (Set<ServiceProducer>) Whitebox.invokeMethod(tested, "getAllServiceProducers");

		ServiceProducer actual = new ServiceProducer(serviceArtifact.getId(), serviceArtifact.getName(), serviceArtifact.getDataProducers());

		Assertions.assertEquals(actual, serviceProducers.stream().findFirst().get());

		Mockito.verify(providerDaoMock).getAllServiceProducers();

	}

	@Test
	public void getAllServiceProducers_empty() throws Exception {
		DataProducer dataProducer = new DataProducer(0, "DataProducer");
		ServiceArtifact serviceArtifact = new ServiceArtifact(1, "plainObject", dataProducer);

		Mockito.when(providerDaoMock.getAllServiceProducers()).thenReturn(new HashSet<ServiceArtifact>());

		Set<ServiceProducer> serviceProducers = (Set<ServiceProducer>) Whitebox.invokeMethod(tested, "getAllServiceProducers");

		Set<ServiceProducer> actual = new HashSet<ServiceProducer>();

		Assertions.assertEquals(actual, serviceProducers);

		Mockito.verify(providerDaoMock).getAllServiceProducers();

	}
}
