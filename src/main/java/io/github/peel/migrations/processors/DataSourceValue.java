package io.github.peel.migrations.processors;

import com.google.common.base.Optional;
import io.github.peel.migrations.api.annotations.Migrate;

import javax.sql.DataSource;

public class DataSourceValue {
    public static final String FAILED = "No database found to execute the db migrations";
    protected DataSourceLookup dataSourceLookup = new DataSourceLookup();
    private final Migrate migrate;

    public DataSourceValue(Migrate migrate){
       this.migrate = migrate;
    }

    public DataSource invoke() {
        final Optional<String> datasource = Optional.fromNullable(migrate.datasource());
        if (datasource.isPresent()) {
            String jndi = datasource.get();
            return dataSourceLookup.getDataSource(jndi);
        } else {
            throw new IllegalStateException(FAILED);
        }
    }

}