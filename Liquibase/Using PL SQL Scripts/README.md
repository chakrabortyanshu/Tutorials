# liquibase-poc
This repo is a simple proof of concept of the Liquibase tool (https://www.liquibase.org). Please, follow the instructions below in order to test this PoC in your local system.

By the moment this PoC only supports Oracle 12c database, but you can adapt easily to other database vendor.

# How to test this PoC
1. Clone the project (`git clone <liquibase-poc-repo-url>`)
2. Update the parameters: *url*, *user* and *password* in the *Liquibase.properties* file, according to your local environment
3. Run the command: `./liquibase update`

At this momment all the changelogs were run in your local DB. Basicly this changelogs create the new table *liquibase_poc_person*.

Also, Liquibase creates the tables *databasechangelog* and *databasechangeloglock* to track the changesets and DB tags that are created in changelogs.

# How to rollback the changes 
You can rollback the changes to an specific tag using the command:
- `./liquibase rollback <tag>`

In this PoC the available tags are:
- *db.release-1.0.0*
- *db.release-1.1.0*
