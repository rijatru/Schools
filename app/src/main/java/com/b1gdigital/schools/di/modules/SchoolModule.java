package com.b1gdigital.schools.di.modules;

import com.b1gdigital.schools.di.scopes.AppScope;
import com.b1gdigital.schools.model.Grade;
import com.b1gdigital.schools.model.School;
import com.b1gdigital.schools.model.Student;
import com.b1gdigital.schools.model.Teacher;
import com.b1gdigital.schools.workers.NetWorker;

import javax.inject.Singleton;

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
    @Singleton
    Grade provideGrade() {

        return new Grade();
    }

    @Provides
    @Singleton
    Teacher provideTeacher() {

        return new Teacher();
    }

    @Provides
    @Singleton
    Student provideStudent() {

        return new Student();
    }

    @Provides
    @Singleton
    NetWorker provideNetWorker() {

        return new NetWorker();
    }
}
