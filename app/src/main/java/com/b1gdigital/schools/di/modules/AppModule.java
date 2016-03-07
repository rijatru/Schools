package com.b1gdigital.schools.di.modules;

import com.b1gdigital.schools.App;
import com.b1gdigital.schools.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    App application;

    public AppModule(App application) {

        this.application = application;
    }

    @Provides
    @AppScope
    App providesApplication() {

        return application;
    }
}
