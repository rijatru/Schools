package com.b1gdigital.schools.di.modules;

import com.b1gdigital.schools.di.scopes.NetScope;
import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.DbWorker;
import com.b1gdigital.schools.workers.LogWorker;
import com.b1gdigital.schools.workers.NetWorker;
import com.b1gdigital.schools.workers.SharedPreferencesWorker;

import dagger.Module;
import dagger.Provides;

@Module
public class NetModule {

    public NetModule() {

    }

    @Provides
    @NetScope
    SharedPreferencesWorker provideSharedPreferences(){

        return new SharedPreferencesWorker();
    }

    @Provides
    @NetScope
    NetWorker provideNetWorker(){

        return new NetWorker();
    }

    @Provides
    @NetScope
    DbWorker provideDbWorker(){

        return new DbWorker();
    }

    @Provides
    @NetScope
    LogWorker provideLogWorker(){

        return new LogWorker();
    }

    @Provides
    @NetScope
    BusWorker provideBusWorker(){

        return new BusWorker();
    }
}
