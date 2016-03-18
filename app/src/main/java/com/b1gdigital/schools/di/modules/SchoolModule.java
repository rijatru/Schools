package com.b1gdigital.schools.di.modules;

import com.b1gdigital.schools.di.scopes.AppScope;
import com.b1gdigital.schools.model.Grade;
import com.b1gdigital.schools.model.School;
import com.b1gdigital.schools.model.Student;
import com.b1gdigital.schools.model.Teacher;

import dagger.Module;
import dagger.Provides;

@Module
public class SchoolModule {

    @Provides
    @AppScope
    School provideSchool() {

        return new School();
    }

    @Provides
    @AppScope
    Grade provideGrade() {

        return new Grade();
    }

    @Provides
    @AppScope
    Teacher provideTeacher() {

        return new Teacher();
    }

    @Provides
    @AppScope
    Student provideStudent() {

        return new Student();
    }
}
