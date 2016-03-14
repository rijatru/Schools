package com.b1gdigital.schools.di.modules;

import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.DbWorker;
import com.b1gdigital.schools.workers.LogWorker;
import com.b1gdigital.schools.workers.SharedPreferencesWorker;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetModule {

    @Provides
    @Singleton
    SharedPreferencesWorker provideSharedPreferences(){

        return new SharedPreferencesWorker();
    }

    @Provides
    @Singleton
    DbWorker provideDbWorker(){

        return new DbWorker();
    }

    @Provides
    @Singleton
    LogWorker provideLogWorker(){

        return new LogWorker();
    }

    @Provides
    @Singleton
    BusWorker provideBusWorker(){

        return new BusWorker();
    }
}
