package io.github.peel.migrations.migrators;

import io.github.peel.migrations.api.annotations.Migrate;
import io.github.peel.migrations.processors.MigrateAnnotationProcessor;
import org.h2.jdbcx.JdbcDataSource;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;

public class MigratorTest {
    private final String ACCOUNT_SIZE = "SELECT COUNT(*) AS %s FROM ACCOUNT";
    private final String ACCOUNT_SIZE_COUNT = "total";
    private final int ACCOUNT_INSERT_NUMBER_NO_TEST = 1;
    private final int ACCOUNT_INSERT_NUMBER_W_TEST = 2;
    private String dbUser = "sa";
    private String dbPass = "";
    private String dbUrl = "jdbc:h2:mem:migrationtest;DB_CLOSE_DELAY=5;MODE=Oracle;TRACE_LEVEL_SYSTEM_OUT=1;";
    private JdbcDataSource ds;

    @Before
    public void setUp() throws Exception {
        ds = new JdbcDataSource();
        ds.setURL(dbUrl);
        ds.setUser(dbUser);
        ds.setPassword(dbPass);
        ds.getConnection().createStatement().execute("shutdown");
    }

    @Test
    public void migratesClassesWithDataSource() throws SQLException {
        MigratorStub migrator = new MigratorStub("io.github.peel.migrations.migrators.datasource_only");
        migrator.process();
        assertThat(verifyAccountSize(), Matchers.is(ACCOUNT_INSERT_NUMBER_NO_TEST));
    }

    @Test
    public void migratesClassesWithDataSourceAndLocations() throws SQLException {
        MigratorStub migrator = new MigratorStub("io.github.peel.migrations.migrators.datasource_locations");
        migrator.process();
        assertThat(verifyAccountSize(), Matchers.is(ACCOUNT_INSERT_NUMBER_W_TEST));
    }

    @Test
    public void invalidDatasourceValue() throws SQLException {
        MigratorStub migrator = new MigratorStub("io.github.peel.migrations.migrators.invalid_datasource");
        migrator.process();
    }

    private int verifyAccountSize() throws SQLException {
        ResultSet set = executeQuery(String.format(ACCOUNT_SIZE, ACCOUNT_SIZE_COUNT));
        int total = 0;
        while(set.next()){
            total = set.getInt(ACCOUNT_SIZE_COUNT);
        }
        return total;
    }

    private ResultSet executeQuery(String query) throws SQLException {
        return ds.getConnection().createStatement().executeQuery(query);
    }

    private class MigratorStub extends MigrateAnnotationProcessor {
        public MigratorStub(String path){
            super(path);
        }
        protected DataSource getDataSource(Migrate migrate) {
            return ds;
        }
    }

}
