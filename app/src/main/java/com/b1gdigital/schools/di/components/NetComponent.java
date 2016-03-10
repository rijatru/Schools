package com.b1gdigital.schools.di.components;

import com.b1gdigital.schools.App;
import com.b1gdigital.schools.MainActivity;
import com.b1gdigital.schools.adapter.StudentsRecyclerViewAdapter;
import com.b1gdigital.schools.di.modules.NetModule;
import com.b1gdigital.schools.di.scopes.NetScope;
import com.b1gdigital.schools.fragments.AddStudent;
import com.b1gdigital.schools.fragments.ShowStudents;
import com.b1gdigital.schools.workers.BusWorker;
import com.b1gdigital.schools.workers.DbWorker;
import com.b1gdigital.schools.workers.LogWorker;
import com.b1gdigital.schools.workers.NetWorker;
import com.b1gdigital.schools.workers.SharedPreferencesWorker;

import dagger.Component;

@NetScope
@Component(modules = {NetModule.class})
public interface NetComponent {

    void inject(App app);

    void inject(MainActivity activity);

    void inject(ShowStudents fragment);

    void inject(AddStudent fragment);

    void inject(StudentsRecyclerViewAdapter studentsRecyclerViewAdapter);

    NetWorker provideNetWorker();

    DbWorker provideDbWorker();

    LogWorker provideLogWorker();

    SharedPreferencesWorker provideSharedPreferences();

    BusWorker provideBusWorker();
}
