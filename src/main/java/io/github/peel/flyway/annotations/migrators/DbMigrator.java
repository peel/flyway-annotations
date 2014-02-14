package io.github.peel.flyway.annotations.migrators;

public interface DbMigrator {
    DbMigrator set();
    void migrate();
}
