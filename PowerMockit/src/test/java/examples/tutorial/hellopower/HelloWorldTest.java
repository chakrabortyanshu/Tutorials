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
package examples.tutorial.hellopower;

import hellopower.HelloWorld;
import hellopower.SimpleConfig;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * The purpose of this test is to get 100% coverage of the {@link HelloWorld}
 * class without any code changes to that class. To achieve this you need learn
 * how to mock static methods.
 * <p>
 * While doing this tutorial please refer to the documentation on how to mock
 * static methods at the PowerMock web site.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(SimpleConfig.class)
public class HelloWorldTest {

	@Test
	public void testGreeting() {
		PowerMockito.mockStatic(SimpleConfig.class);
		Mockito.when(SimpleConfig.getGreeting()).thenReturn("Mock Greeting");
		Mockito.when(SimpleConfig.getTarget()).thenReturn("Mock Target");

		Assertions.assertEquals("Mock Greeting Mock Target", new HelloWorld().greet());

		PowerMockito.verifyStatic(SimpleConfig.class);
		SimpleConfig.getGreeting();

		PowerMockito.verifyStatic(SimpleConfig.class);
		SimpleConfig.getTarget();

	}
}
