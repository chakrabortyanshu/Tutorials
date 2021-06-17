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
package examples.tutorial.staticmocking.impl;

import impl.IdGenerator;
import impl.ServiceRegistrator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import osgi.BundleContext;
import osgi.ServiceRegistration;

import javax.xml.ws.Service;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The purpose of this test is to get 100% coverage of the
 * {@link ServiceRegistrator} class without any code changes to that class. To
 * achieve this you need learn how to mock static methods as well as how to set
 * and get internal state of an object.
 * <p>
 * While doing this tutorial please refer to the documentation on how to mock
 * static methods and bypass encapsulation at the PowerMock web site.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ServiceRegistrator.class, IdGenerator.class})
public class ServiceRegistratorTest {

	private BundleContext bundleContextMock;
	private ServiceRegistration serviceRegistrationMock;
	private ServiceRegistrator tested;

	@Before
	public void setUp() {
		bundleContextMock = Mockito.mock(BundleContext.class);
		serviceRegistrationMock = Mockito.mock(ServiceRegistration.class);
		tested = new ServiceRegistrator();

		Whitebox.setInternalState(tested, "bundleContext", bundleContextMock);

		PowerMockito.mockStatic(IdGenerator.class);
	}

	@After
	public void tearDown() {
		bundleContextMock = null;
		serviceRegistrationMock = null;
		tested = null;
	}

	/**
	 * Test for the {@link ServiceRegistrator#registerService(String, Object)}
	 * method.
	 * 
	 * @throws Exception
	 *             If an error occurs.
	 */
	@Test
	public void testRegisterService() throws Exception {

		String name = "NAME";
		Object serviceImplementation = new Object();

		Mockito.when(bundleContextMock.registerService(name, serviceImplementation,null)).thenReturn(serviceRegistrationMock);

		//PowerMockito.doReturn(100L).when(IdGenerator.generateNewId());
		PowerMockito.when(IdGenerator.generateNewId()).thenReturn(100L);

		long id = tested.registerService(name, serviceImplementation);

		Assertions.assertEquals(100L, id);

		PowerMockito.verifyStatic(IdGenerator.class);
		IdGenerator.generateNewId();

		Mockito.verify(bundleContextMock).registerService(name, serviceImplementation,null);
	}

	/**
	 * Test for the {@link ServiceRegistrator#unregisterService(long)} method.
	 * 
	 * @throws Exception
	 *             If an error occurs.
	 */
	@Test
	public void testUnregisterService() throws Exception {

		ConcurrentHashMap<Long, ServiceRegistration> serviceRegistrationMap = new ConcurrentHashMap<>();

		serviceRegistrationMap.put(105L, serviceRegistrationMock);

		Whitebox.setInternalState(tested, "serviceRegistrations", serviceRegistrationMap);

		tested.unregisterService(105L);

		Assertions.assertEquals(0, serviceRegistrationMap.size());
		Mockito.verify(serviceRegistrationMock).unregister();

	}

	/**
	 * Test for the {@link ServiceRegistrator#unregisterService(long)} method
	 * when the ID doesn't exist.
	 * 
	 * @throws Exception
	 *             If an error occurs.
	 */
	@Test
	public void testUnregisterService_idDoesntExist() throws Exception {

		ConcurrentHashMap<Long, ServiceRegistration> serviceRegistrationMap = new ConcurrentHashMap<>();

		serviceRegistrationMap.put(105L, serviceRegistrationMock);

		Whitebox.setInternalState(tested, "serviceRegistrations", serviceRegistrationMap);

		Assertions.assertThrows(IllegalStateException.class, () -> tested.unregisterService(105L +1));
		Assertions.assertEquals(1, serviceRegistrationMap.size());
		Mockito.verify(serviceRegistrationMock, Mockito.never()).unregister();

	}
}
