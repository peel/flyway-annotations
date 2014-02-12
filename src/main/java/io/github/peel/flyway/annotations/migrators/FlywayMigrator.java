package io.github.peel.flyway.annotations.migrators;

import com.googlecode.flyway.core.Flyway;

import javax.sql.DataSource;

class FlywayMigrator implements DbMigrator {
    private final String[] migrations;
    private final DataSource dataSource;
    private Flyway flyway = new Flyway();

    private FlywayMigrator(DataSource dataSource, String... migrations){
        this.migrations = migrations;
        this.dataSource = dataSource;
    }

    public static FlywayMigrator of(DataSource dataSource, String... migrations){
        return new FlywayMigrator(dataSource, migrations);
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
