package io.github.peel.migrations.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines a configuration annotation for migration.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Migrate {
    /**
     * Configures JNDI name for datasource
     * @return
     */
    String datasource();

    /**
     * Configures locations for migrations
     * @return
     */
    String[] migrations() default {"db/migrations"};
}
