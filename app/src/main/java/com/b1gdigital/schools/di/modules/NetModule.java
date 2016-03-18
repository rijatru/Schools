package com.b1gdigital.schools.di.modules;

import com.b1gdigital.schools.di.scopes.AppScope;
import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.DbWorker;
import com.b1gdigital.schools.workers.LogWorker;
import com.b1gdigital.schools.workers.NetWorker;
import com.b1gdigital.schools.workers.SharedPreferencesWorker;

import dagger.Module;
import dagger.Provides;

@Module
public class NetModule {

    @Provides
    @AppScope
    SharedPreferencesWorker provideSharedPreferences(){

        return new SharedPreferencesWorker();
    }

    @Provides
    @AppScope
    DbWorker provideDbWorker(){

        return new DbWorker();
    }

    @Provides
    @AppScope
    LogWorker provideLogWorker(){

        return new LogWorker();
    }

    @Provides
    @AppScope
    BusWorker provideBusWorker(){

        return new BusWorker();
    }

    @Provides
    @AppScope
    NetWorker provideNetWorker() {

        return new NetWorker();
    }
}
