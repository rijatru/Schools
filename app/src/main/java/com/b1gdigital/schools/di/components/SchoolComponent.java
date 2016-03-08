package com.b1gdigital.schools.di.components;

import com.b1gdigital.schools.App;
import com.b1gdigital.schools.MainActivity;
import com.b1gdigital.schools.di.modules.SchoolModule;
import com.b1gdigital.schools.di.scopes.SchoolScope;
import com.b1gdigital.schools.fragments.FragmentTest;
import com.b1gdigital.schools.models.School;

import dagger.Component;

@SchoolScope
@Component(modules = {SchoolModule.class})
public interface SchoolComponent {

    void inject(MainActivity activity);

    void inject(FragmentTest fragment);

    void inject(App app);

    School provideSchool();
}
