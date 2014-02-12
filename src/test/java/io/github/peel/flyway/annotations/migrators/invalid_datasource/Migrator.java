package io.github.peel.flyway.annotations.migrators.invalid_datasource;

import io.github.peel.flyway.annotations.annotations.Migrate;

@Migrate(datasource="")
interface Migrator{}

