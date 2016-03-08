package com.b1gdigital.schools.di.modules;

import com.b1gdigital.schools.di.scopes.SchoolScope;
import com.b1gdigital.schools.models.Grade;
import com.b1gdigital.schools.models.School;
import com.b1gdigital.schools.models.Student;
import com.b1gdigital.schools.models.Teacher;

import dagger.Module;
import dagger.Provides;

@Module
public class SchoolModule {

    @Provides
    @SchoolScope
    School provideSchool() {

        return new School(new Grade(new Teacher()));
    }

    @Provides
    @SchoolScope
    Grade provideGrade() {

        return new Grade(new Teacher());
    }

    @Provides
    @SchoolScope
    Teacher provideTeacher() {

        return new Teacher();
    }

    @Provides
    @SchoolScope
    Student provideStudent() {

        return new Student();
    }
}
