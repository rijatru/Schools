package com.b1gdigital.schools.di.modules;

import com.b1gdigital.schools.di.scopes.SchoolScope;
import com.b1gdigital.schools.model.Grade;
import com.b1gdigital.schools.model.School;
import com.b1gdigital.schools.model.Student;
import com.b1gdigital.schools.model.Teacher;
import com.b1gdigital.schools.workers.NetWorker;

import dagger.Module;
import dagger.Provides;

@Module
public class SchoolModule {

    @Provides
    @SchoolScope
    School provideSchool() {

        return new School();
    }

    @Provides
    @SchoolScope
    Grade provideGrade() {

        return new Grade();
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

    @Provides
    @SchoolScope
    NetWorker provideNetWorker() {

        return new NetWorker();
    }
}
