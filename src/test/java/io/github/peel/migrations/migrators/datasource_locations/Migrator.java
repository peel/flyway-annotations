package io.github.peel.migrations.migrators.datasource_locations;

import io.github.peel.migrations.api.annotations.Migrate;

@Migrate(datasource="test/JndiResource", migrations={"db/test", "db/migrations"})
interface Migrator{}

