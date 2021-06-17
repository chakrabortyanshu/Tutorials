https://docs.liquibase.com/workflows/database-setup-tutorials/oracle.html
https://www.liquibase.org/get-started/quickstart
liquibase --username=test --password=test --changeLogFile=changelog.xml status
liquibase generateChangeLog
liquibase update
liquibase --changeLogFile=changelog.xml update
liquibase rollback <tag>
liquibase rollbackCount 2    --> Rollbacks the last two changes.
liquibase rollbackOneChangeSetSQL --changeSetId=2 --changeSetAuthor=achakrab --changeSetPath=changelog.demo.xml
liquibase rollbackOneChangeSet --changeSetId=2 --changeSetAuthor=achakrab --changeSetPath=changelog.demo.xml --force


liquibase --changeLogFile=mydatabase_changelog.xml generateChangeLog
liquibase registerChangeLog
liquibase update
liquibase rollbackCount 1
liquibase registerChangeLog


https://learn.liquibase.com/
https://docs.liquibase.com/home.html
https://www.liquibase.org/get-started/best-practices
https://www.liquibase.org/get-started
