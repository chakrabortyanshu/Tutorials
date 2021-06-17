# PowerMockit

## Quick summary
- Use the @RunWith(PowerMockRunner.class) annotation at the class-level of the test case.
- Use the @PrepareForTest({ClassThatCallsTheSystemClass.class}) annotation at the class-level of the test case.
- Use mockStatic(SystemClass.class) to mock the system class then setup the expectations as normally.
- EasyMock only: Use PowerMock.replayAll() to change to replay mode.
- EasyMock only: Use PowerMock.verifyAll() to change to verify mode.

## Userful Links
- https://github.com/powermock/powermock/wiki/Mockito
- https://github.com/powermock/powermock/wiki/Code-coverage-with-JaCoCo
- https://github.com/powermock/powermock/wiki/JUnit_Delegating_Runner
- https://github.com/powermock/powermock/wiki/PowerMockRule
- https://github.com/powermock/powermock/wiki/PowerMockAgent
- https://github.com/powermock/powermock
- https://github.com/powermock/powermock-examples-maven
- How to mock java system classes like java.lang etc.[https://github.com/powermock/powermock/wiki/Mock-System]
- https://blog.jayway.com/2009/10/28/untestable-code-with-mockito-and-powermock/
- https://github.com/powermock/powermock-examples-maven

### Git Commands
```
Quick setup — if you’ve done this kind of thing before
or	
https://github.com/chakrabortyanshu/Mockito.git
Get started by creating a new file or uploading an existing file. We recommend every repository include a README, LICENSE, and .gitignore.

…or create a new repository on the command line
echo "# Mockito" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M master
git remote add origin https://github.com/chakrabortyanshu/Mockito.git
git push -u origin master
                
…or push an existing repository from the command line
git remote add origin https://github.com/chakrabortyanshu/Mockito.git
git branch -M master
git push -u origin master
…or import code from another repository
You can initialize this repository with code from a Subversion, Mercurial, or TFS project.

To add remove github url:
	git remote add origin https://github.com/user/repo.git
	git remote -v

To set your global username/email configuration:
	git config --global user.name "chakrabortyanshu"
	git config --global user.email "chakrabortyanshu@gmail.com"
	
To set repository-specific username/email configuration:
	git config user.name "chakrabortyanshu"
	git config user.email "chakrabortyanshu@gmail.com"
	cat .git/config
```

##SonarQube:Version 8.4.2 (build 36762)
Working fine with the latest SonarQube version.
```
mvn clean install sonar:sonar -Dsonar.projectKey=PowerMockito -Dsonar.host.url=http://localhost:9002 -Dsonar.login=1acb9e61bb2610c483135af10d33c6f277768551
```




