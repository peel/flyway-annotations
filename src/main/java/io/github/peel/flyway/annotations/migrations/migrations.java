package io.github.peel.flyway.annotations.migrations;

import io.github.peel.flyway.annotations.annotations.Migrate;

@Migrate(datasource="jdbc/test")
interface Account{}

