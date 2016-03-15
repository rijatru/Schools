package com.b1gdigital.schools.di.components;

import com.b1gdigital.schools.App;
import com.b1gdigital.schools.MainActivity;
import com.b1gdigital.schools.adapter.StudentsRecyclerViewAdapter;
import com.b1gdigital.schools.di.modules.NetModule;
import com.b1gdigital.schools.di.modules.SchoolModule;
import com.b1gdigital.schools.di.scopes.AppScope;
import com.b1gdigital.schools.fragments.AddStudent;
import com.b1gdigital.schools.fragments.ShowStudents;

@AppScope
@dagger.Component(modules = {SchoolModule.class, NetModule.class})
public interface AppComponent {

    void inject(App app);

    void inject(MainActivity activity);

    void inject(ShowStudents fragment);

    void inject(AddStudent fragment);

    void inject(StudentsRecyclerViewAdapter studentsRecyclerViewAdapter);
}
