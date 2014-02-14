package io.github.peel.flyway.annotations.migrators;

import com.googlecode.flyway.core.Flyway;

import javax.sql.DataSource;

class FlywayMigrator implements DbMigrator {
    private final String[] migrations;
    private final DataSource dataSource;
    private Flyway flyway = new Flyway();

    public FlywayMigrator(DataSource dataSource, String... migrations){
        this.migrations = migrations;
        this.dataSource = dataSource;
    }

    @Override
    public FlywayMigrator set() {
        flyway = new Flyway();
        flyway.setLocations(migrations);
        flyway.setInitOnMigrate(true);
        flyway.setDataSource(dataSource);
        return this;
    }
    @Override
    public void migrate(){
        flyway.migrate();
    }

}
