#Description
Migration library is an annotation-based API for FlywayDB. Flyway is a database migration tool, that solves problem of versioning database schemas, applying SQL scripts onto databases and all that pointless work.
For more info see: http://flywaydb.org/getstarted/howFlywayWorks.html

#Usage
To make use of the library initialize annotation processor by pointing out the package storing configuration interfaces:

    public void onStartup(){
        Migrators.forPackage("com.nordea.nemis.poc.db.migrations").process();
    }

Configuration interfaces are defined as follows:

    @Migrate(datasource="jdbc/Foo")
    interface Foo{}

By default scripts are stored in the db/migrations dir in the $CLASSPATH. In multi-module project (ie. project-db, packaged into a war or ear) you'll need to remember about the classloader hierarchy, thus it is helpful to override the migration locations by setting appropriate option.

For more complex usecases use:

    @Migrate(datasource="jdbc/Foo", migrations={"db/migrations/foo"})
    interface Foo{}

    @Migrate(datasource="jdbc/Bar", migrations={"db/migrations/foo"})
    interface Bar{}

#Downgrade
Flyway does not support downgrades. If changing the data structure needs a downgrade script, make sure to prepare one separately. All in all try to keep the backwards compatibility at all times.