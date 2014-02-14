package io.github.peel.migrations.processors;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

import javax.naming.NamingException;
import javax.sql.DataSource;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class DataSourceLookupTest {
    @Test
    public void givenAValidJndiNameReturnsDataSource() throws NamingException {
        DataSourceLookup dsl = new DataSourceLookupTester();
        DataSource actual = dsl.getDataSource("test/jndi");
        assertNotNull(actual);
        assertThat(actual, instanceOf(DataSource.class));
    }

    private class DataSourceLookupTester extends DataSourceLookup{
        protected Object getFromContext(String jndi) throws NamingException {
            return new JdbcDataSource();
        }
    }
}
