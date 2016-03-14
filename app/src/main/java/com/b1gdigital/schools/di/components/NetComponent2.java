package com.b1gdigital.schools.di.components;

import com.b1gdigital.schools.App;
import com.b1gdigital.schools.MainActivity;
import com.b1gdigital.schools.adapter.StudentsRecyclerViewAdapter;
import com.b1gdigital.schools.di.modules.NetModule2;
import com.b1gdigital.schools.di.scopes.NetScope2;
import com.b1gdigital.schools.fragments.AddStudent;
import com.b1gdigital.schools.fragments.ShowStudents;

import dagger.Component;

@NetScope2
@Component(modules = {NetModule2.class})
public interface NetComponent2 {

    void inject(App app);

    void inject(MainActivity activity);

    void inject(ShowStudents fragment);

    void inject(AddStudent fragment);

    void inject(StudentsRecyclerViewAdapter studentsRecyclerViewAdapter);
}
