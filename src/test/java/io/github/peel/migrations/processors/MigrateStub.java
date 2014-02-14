package io.github.peel.migrations.processors;

import com.google.common.base.Optional;
import io.github.peel.migrations.api.annotations.Migrate;

import java.lang.annotation.Annotation;

class MigrateStub implements Migrate {
    private final String datasource;
    private final String[] migrations;

    public MigrateStub(String datasource, Optional<String[]> migrations){
        this.datasource = datasource;
        if(migrations.isPresent()){
            this.migrations = migrations.get();
        }else{
           this.migrations = new String[]{"db/migrations"};
        }
    }
    @Override
    public String datasource() {
        return datasource;
    }

    @Override
    public String[] migrations() {
        return migrations;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
