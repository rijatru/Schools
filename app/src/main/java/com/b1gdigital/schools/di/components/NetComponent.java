package com.b1gdigital.schools.di.components;

import com.b1gdigital.schools.di.modules.AppModule;
import com.b1gdigital.schools.di.modules.NetModule;
import com.b1gdigital.schools.di.scopes.NetScope;
import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.DbWorker;
import com.b1gdigital.schools.workers.LogWorker;
import com.b1gdigital.schools.workers.NetWorker;
import com.b1gdigital.schools.workers.SharedPreferencesWorker;

import dagger.Component;

@NetScope
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {

    NetWorker netWorker();
    DbWorker dbWorker();
    LogWorker logWorker();
    SharedPreferencesWorker sharedPreferences();
    BusWorker BusWorker();
}
