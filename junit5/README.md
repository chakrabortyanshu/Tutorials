# Learning junit5

https://junit.org/junit5/docs/current/user-guide/#running-tests-build-maven-engines-configure

## Running Pitest
```
Goals: org.pitest:pitest-maven:mutationCoverage
```

## Maven Commands
```
mvn clean test 
mvn surefire:test 
mvn sonar:sonar -Dsonar.projectKey=junit5 -Dsonar.host.url=http://localhost:9003 -Dsonar.login=2855e1db9d2fbaefccb86eb9e2e609b4f3e6481d
mvn org.pitest:pitest-maven:mutationCoverage
```

## Docker Container Commands:
- docker pull sonarqube:7.9.4-community
- docker run -d --name sonarqube.7.9.4-community -p 9003:9000 sonarqube:7.9.4-community

## Other Docker Container Commands: (not to be used in this project)
- docker run -d --name sonarqube -p 9001:9000 sonarqube:latest
- docker run -d --name sonarqube.8.4.2-community -p 9002:9000 sonarqube:8.4.2-community

## Important notes
I have tried running the sonar with sonarqube:8.4.2-community but it was not showing me the code coverage in sonarqube.
Until I could find the reason why it is not working, I will not upgrade to the latest version and continue to use 
sonarqube.7.9.4-community because it is showing me the code coverage with maven.

## Git commands
1. To add remove github url:
	- git remote add origin https://github.com/user/repo.git
	- git remote -v
2. To set your **global** username/email configuration:
	- git config **--global** user.name "user name"
	- git config **--global** user.email "username@example.com"
3. To set repository-specific username/email configuration:
	- git config user.name "user name"
	- git config user.email "username@example.com"
	- cat .git/config

## Useful links:
- https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-maven/
- https://github.com/SonarSource/sonar-scanning-examples/tree/master/sonarqube-scanner-maven/maven-basic/src
- https://hub.docker.com/_/sonarqube?tab=tags
- https://www.eclemma.org/jacoco/trunk/doc/maven.html
- https://github.com/powermock/powermock/wiki/Code-coverage-with-JaCoCo
- https://docs.github.com/en/github/writing-on-github/basic-writing-and-formatting-syntax

