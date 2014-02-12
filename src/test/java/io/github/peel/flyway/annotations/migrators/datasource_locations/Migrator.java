package io.github.peel.flyway.annotations.migrators.datasource_locations;

import io.github.peel.flyway.annotations.annotations.Migrate;

@Migrate(datasource="test/JndiResource", migrations={"db/test", "db/migrations"})
interface Migrator{}

