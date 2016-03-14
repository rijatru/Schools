package com.b1gdigital.schools.di.modules;

import com.b1gdigital.schools.di.scopes.NetScope2;
import com.b1gdigital.schools.model.Grade;
import com.b1gdigital.schools.model.School;
import com.b1gdigital.schools.model.Student;
import com.b1gdigital.schools.model.Teacher;

import dagger.Module;
import dagger.Provides;

@Module
public class NetModule2 {

    @Provides
    @NetScope2
    School provideSchool() {

        return new School();
    }

    @Provides
    @NetScope2
    Grade provideGrade() {

        return new Grade();
    }

    @Provides
    @NetScope2
    Teacher provideTeacher() {

        return new Teacher();
    }

    @Provides
    @NetScope2
    Student provideStudent() {

        return new Student();
    }
}
