https://www.youtube.com/watch?v=ynLRGrRwirc
https://www.youtube.com/watch?v=SaDUsSHXoQg
https://www.youtube.com/watch?v=_I94-tJlovg

https://github.com/jmockit/jmockit1
http://jmockit.github.io/tutorial/Introduction.html
https://stackoverflow.com/questions/2905735/jmockit-initialization-problem/4369038
https://repo.maven.apache.org/maven2/org/jmockit/jmockit/1.43/
http://dontpanic.42.nl/2013/04/mockito-powermock-vs-jmockit.html

Software Testing Automation Framework (STAF)

Sonar
eclemma
findbug

automate the testing.

---------------------------------------------
http://staf.sourceforge.net/




@Mocked
DBManager dbManager;

new MockUp<DBManager>(){
	@Mock
	public String retrieveAccountHolderName(int accountId){
		return "abcd";
	}
}
=========================================================================
@Injectable
DBManager dbManager;

new Expectations(){
	{
		dbManager.retrieveAccountHolderName(10);  //if param is fixed use Expectations
		result = "efgh";
		times = 2;
		//maxTimes = 3;
	}
}
=========================================================================
//mocking private method.
new MockUp<PrivateDemo>(){
	@Mock
	String isPrivate(){  //Never specify access param as private here when mocking private method from original class.
		return "Mockup Invoked";
	}
}

PrivateDemo privateDemo = new PrivateDemo();
String actualResponse = privateDemo.publicCallsPrivate();
assertEquals("Mockup Invoked for Private method ", "Mockup Invoked", actualResponse);
=========================================================================
//mocking static method
new MockUp<StaticMethodOriginal>(){
	@Mock
	
}
=========================================================================
=========================================================================
=========================================================================
=========================================================================
=========================================================================
=========================================================================
=========================================================================
=========================================================================
Software Testing Automation Framework (STAF)
---------------------------------------------
http://staf.sourceforge.net/


