package io.github.peel.migrations.api;

/**
 * API interface for migrators.
 * Currently only FlyWay is supported
 */
public interface Migrator {
    public void process();
}
