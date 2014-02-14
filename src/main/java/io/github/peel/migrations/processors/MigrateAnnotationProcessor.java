package io.github.peel.migrations.processors;

import io.github.peel.migrations.api.Migrator;
import io.github.peel.migrations.api.annotations.Migrate;
import io.github.peel.migrations.migrators.FlywayMigrator;
import org.reflections.Reflections;

import javax.sql.DataSource;
import java.util.Set;

public class MigrateAnnotationProcessor implements Migrator {
    private final Reflections reflections;

    public MigrateAnnotationProcessor(String path){
        reflections = new Reflections(path);
    }

    public void process() {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Migrate.class);
        for(Class<?> c : annotated){
            Migrate migrate = c.getAnnotation(Migrate.class);
            String[] migrations = getMigrations(migrate);
            DataSource dataSource = getDataSource(migrate);
            migrate(dataSource, migrations);
        }
    }

    protected DataSource getDataSource(Migrate migrate) {
        return new DataSourceValue(migrate).invoke();
    }

    protected String[] getMigrations(Migrate migrate) {
        return new MigrationsValue(migrate).invoke();
    }

    private void migrate(DataSource dataSource, String[] migrations) {
        new FlywayMigrator(dataSource, migrations).set().migrate();
    }

}
