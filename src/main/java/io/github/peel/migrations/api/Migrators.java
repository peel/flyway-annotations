package io.github.peel.migrations.api;

import io.github.peel.migrations.processors.MigrateAnnotationProcessor;

/**
 * Construction API for {@link Migrator}
 */
public class Migrators {
    private Migrators() {
    }

    /**
     * Creates a default {@link Migrator} implementation.
     * @param fully qualified package pointing to {@link io.github.peel.migrations.api.annotations.Migrate} annotated migration configurations.
     * @return {@link Migrator}
     */
    public static Migrator forPackage(String s){
        return new MigrateAnnotationProcessor(s);
    }
}
