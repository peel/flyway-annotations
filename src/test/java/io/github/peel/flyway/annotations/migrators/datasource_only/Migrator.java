package io.github.peel.flyway.annotations.migrators.datasource_only;

import io.github.peel.flyway.annotations.annotations.Migrate;

@Migrate(datasource="test/JndiResource")
interface Migrator{}
