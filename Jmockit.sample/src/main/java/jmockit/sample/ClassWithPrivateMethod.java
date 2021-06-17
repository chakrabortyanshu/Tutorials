package jmockit.sample;

import java.io.File;

public class ClassWithPrivateMethod {

	private static File myDir;
	
	private int sum(int a, int b) {
		return a+b;
	}
		
	private static String findFile(String fileName) {
		for(final String actualFileName: myDir.list()) {
			if(actualFileName.startsWith(fileName)) {
				return actualFileName;
			}
		}
		return null;
	}
	
	public void setFile(File myDir) {
		this.myDir=myDir;
	}
	
}


