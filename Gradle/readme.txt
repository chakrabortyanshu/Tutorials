https://appmediation.com/how-to-add-local-libraries-to-gradle/





1. So that the pom file is not looked for during dependency download

	repositories {
	   
		maven { url  'https://artifactory.com/maven-abcd'
			metadataSources {
				artifact()
			}
		}

	}

2. gradlew.bat build jacocoTestReport

3. gradlew.bat clean build jacocoTestReport sonarqube -Dsonar.projectKey=AW1151001_ABCD_ATM -Dsonar.host.url=http://localhost:9002 -Dsonar.login=814a256dbbdb13865985bd1c0c5b607f63677c8d 



