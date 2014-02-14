package io.github.peel.migrations.migrators;

import com.googlecode.flyway.core.Flyway;

import javax.sql.DataSource;

/**
 * Backend API wrapper for FlyWayDB
 */
public class FlywayMigrator{
    private final String[] migrations;
    private final DataSource dataSource;
    private Flyway flyway = new Flyway();

    public FlywayMigrator(DataSource dataSource, String... migrations){
        this.migrations = migrations;
        this.dataSource = dataSource;
    }

    public FlywayMigrator set() {
        flyway = new Flyway();
        flyway.setLocations(migrations);
        flyway.setInitOnMigrate(true);
        flyway.setDataSource(dataSource);
        return this;
    }

    public void migrate(){
        flyway.migrate();
    }

}
