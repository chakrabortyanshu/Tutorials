package jmockit.sample;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;

public class TestStaticManager {

	@Tested
	private StaticManager staticManager;
		
	@Test
	public void testStaticMethod() {
		
		new MockUp<ManagerService>() {
			@Mock
			public String getValue() {
				return "Mocked Value";
			}
		};
		
		
		String value = staticManager.getValue();
		
		assertEquals("Mocked Value", value);
		
	}
}
