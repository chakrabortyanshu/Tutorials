package jmockit.sample;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;

public class TestClassWithPrivateMethod {

	@Tested
	private ClassWithPrivateMethod classWithPrivateMethod;
	
	
	
	@Test
	public void shouldGetSum(){
		
		int sum = Deencapsulation.invoke(classWithPrivateMethod, "sum", 1,2);
		
		assertEquals(3, sum);
		
	}
	
	@Test
	public void testClassA(@Mocked final File myDir){
		
		final String[] files = {"file1-bla.txt"};
		
		/*new NonStrictExpectations() {{
			myDir.list();
			result = files;
		}};*/
		
		new Expectations() {{
			myDir.list();
			result = files;
		}};
		
		
		classWithPrivateMethod.setFile(myDir);
		
		String returnedFileName = Deencapsulation.invoke(ClassWithPrivateMethod.class, "findFile", "file1");
		
		assertEquals("file1-bla.txt", returnedFileName);
	}

	
	
}
