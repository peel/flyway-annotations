package io.github.peel.migrations.processors;

import com.google.common.base.Optional;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceLookup {

    public DataSource getDataSource(String jndi) {
        Optional<DataSource> ds = lookupDataSource(jndi);
        if (ds.isPresent()) {
            return ds.get();
        } else {
            throw new IllegalStateException(DataSourceValue.FAILED);
        }
    }

    private Optional<DataSource> lookupDataSource(String jndi) {
        try {
            Object lookup = getFromContext(jndi);
            if (lookup instanceof DataSource) {
                return Optional.of((DataSource) lookup);
            } else {
                return Optional.absent();
            }
        } catch (NamingException e) {
            return Optional.absent();
        }
    }

    protected Object getFromContext(String jndi) throws NamingException {
        InitialContext ctx = new InitialContext();
        return ctx.lookup(jndi);
    }
}