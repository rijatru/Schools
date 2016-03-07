package com.b1gdigital.schools.di.components;

import com.b1gdigital.schools.MainActivity;
import com.b1gdigital.schools.di.modules.SchoolModule;
import com.b1gdigital.schools.di.scopes.SchoolScope;

import dagger.Component;

@SchoolScope
@Component(dependencies={GradeComponent.class}, modules={SchoolModule.class})
public interface SchoolComponent {

    void inject(MainActivity activity);
}
