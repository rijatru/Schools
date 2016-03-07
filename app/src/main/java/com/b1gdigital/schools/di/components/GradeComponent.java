package com.b1gdigital.schools.di.components;

import com.b1gdigital.schools.di.modules.AppModule;
import com.b1gdigital.schools.di.modules.GradeModule;
import com.b1gdigital.schools.di.scopes.GradeScope;
import com.b1gdigital.schools.models.Grade;
import com.b1gdigital.schools.models.School;
import com.b1gdigital.schools.models.Student;
import com.b1gdigital.schools.models.Teacher;

import dagger.Component;

@GradeScope
@Component(modules={AppModule.class, GradeModule.class})
public interface GradeComponent {

    Grade grade();
    School school();
    Teacher teacher();
    Student student();
}
