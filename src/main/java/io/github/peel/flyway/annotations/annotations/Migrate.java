package io.github.peel.flyway.annotations.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Migrate {
    String datasource();
    String[] migrations() default {"db/migrations"};
}
