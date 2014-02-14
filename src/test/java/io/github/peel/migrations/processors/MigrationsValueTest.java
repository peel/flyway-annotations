package io.github.peel.migrations.processors;

import com.google.common.base.Optional;
import io.github.peel.migrations.api.annotations.Migrate;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class MigrationsValueTest {
    public static final String JNDI = "jdbc/test";
    public static final String[] MIGRATIONS = new String[]{"db/migrations", "db/test"};
    public static final String[] DEFAULT_MIGRATIONS = new String[]{"db/migrations"};

    @Test
    public void givenMigrationsInAnnotationReturnsAnArray() throws Exception {
        Migrate migrate = new MigrateStub(JNDI, Optional.of(MIGRATIONS));
        String[] migrations = new MigrationsValue(migrate).invoke();
        assertThat(migrations, Matchers.arrayContainingInAnyOrder(MIGRATIONS));
    }
    @Test
    public void givenEmptyMigrationsInAnnotationReturnsDefaults() throws Exception {
        Migrate migrate = new MigrateStub(JNDI, Optional.<String[]>absent());
        String[] migrations = new MigrationsValue(migrate).invoke();
        assertThat(migrations, Matchers.arrayContainingInAnyOrder(DEFAULT_MIGRATIONS));
    }
}
