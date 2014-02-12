package io.github.peel.flyway.annotations.migrators;

import com.google.common.base.Optional;
import io.github.peel.flyway.annotations.annotations.Migrate;
import org.reflections.Reflections;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Set;

public class Migrator {
    private static final String FAILED = "No database found to execute the db migrations";
    private String[] migrations = {"db/migrations"};
    private DataSource dataSource;
    private Reflections reflections;

    public Migrator(String path){
        reflections = new Reflections(path);
    }

    public void process() {
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Migrate.class);
        for(Class<?> c : annotated){
            Migrate migrate = c.getAnnotation(Migrate.class);
            setMigrations(migrate);
            setDataSource(migrate);
        }
    }

    private void setDataSource(Migrate migrate) {
        final Optional<String> datasource = Optional.fromNullable(migrate.datasource());
        if(datasource.isPresent()){
            String jndi = datasource.get();
            Optional<DataSource> ds = findDataSource(jndi);
            if (ds.isPresent()) {
                dataSource=ds.get();
                migrate();
            }else{
                throw new IllegalStateException(FAILED);
            }
        }else{
            throw new IllegalStateException(FAILED);
        }
    }

    private void setMigrations(Migrate migrate) {
        final Optional<String[]> migrations = Optional.fromNullable(migrate.migrations());
        if(migrations.isPresent()){
            this.migrations = migrations.get();
        }
    }

    protected Optional<DataSource> findDataSource(String jndi) {
        try {
           InitialContext ctx = getInitialContext();
           Object lookup = ctx.lookup(jndi);
           if (lookup instanceof DataSource){
                return Optional.of((DataSource)lookup);
           }else{
                return Optional.absent();
           }
        } catch (NamingException e) {
            return Optional.absent();
        }
    }

    private InitialContext getInitialContext() throws NamingException {
        return new InitialContext();
    }

    private void migrate() {
        FlywayMigrator.of(dataSource, migrations).set().migrate();
    }

}
