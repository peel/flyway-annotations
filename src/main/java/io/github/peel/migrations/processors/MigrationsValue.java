package io.github.peel.migrations.processors;

import com.google.common.base.Optional;
import io.github.peel.migrations.api.annotations.Migrate;

public class MigrationsValue {
    private static final String[] DEFAULT_MIGRATIONS = {"db/migrations"};
    private final Migrate migrate;

    public MigrationsValue(Migrate migrate) {
        this.migrate = migrate;
    }

    public String[] invoke() {
        final Optional<String[]> migrations = Optional.fromNullable(migrate.migrations());
        if (migrations.isPresent()) {
            return migrations.get();
        } else {
            return DEFAULT_MIGRATIONS.clone();
        }
    }
}