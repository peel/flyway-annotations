package io.github.peel.migrations.processors;

import com.google.common.base.Optional;
import io.github.peel.migrations.api.annotations.Migrate;
import org.h2.jdbcx.JdbcDataSource;
import org.hamcrest.Matchers;
import org.junit.Test;

import javax.sql.DataSource;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class DataSourceValueTest {
    @Test
    public void givenAValidDataSourceInAnnotationReturnsDataSource() throws Exception {
        Migrate migrate = new MigrateStub("jdbc/test", Optional.<String[]>absent());
        DataSource actual = new DataSourceValueTester(migrate).invoke();
        assertThat(actual, Matchers.isA(DataSource.class));
    }
    @Test(expected=IllegalStateException.class)
    public void givenAnEmptyDataSourceInAnnotationReturnsDataSource() throws Exception {
        Migrate migrate = new MigrateStub(null, Optional.<String[]>absent());
        new DataSourceValueTester(migrate).invoke();
    }

    class DataSourceValueTester extends DataSourceValue{
        public DataSourceValueTester(Migrate migrate) {
            super(migrate);
            this.dataSourceLookup = mock(DataSourceLookup.class);
            when(dataSourceLookup.getDataSource("jdbc/test")).thenReturn(new JdbcDataSource());
        }
    }
}
