package com.b1gdigital.schools.di.modules;

import com.b1gdigital.schools.di.scopes.GradeScope;
import com.b1gdigital.schools.models.Grade;
import com.b1gdigital.schools.models.School;
import com.b1gdigital.schools.models.Student;
import com.b1gdigital.schools.models.Teacher;

import dagger.Module;
import dagger.Provides;

@Module
public class GradeModule {

    public GradeModule() {

    }

    @Provides
    @GradeScope
    School provideSchool(){

        return new School();
    }

    @Provides
    @GradeScope
    Grade provideGrade(){

        return new Grade();
    }

    @Provides
    @GradeScope
    Teacher provideTeacher(){

        return new Teacher();
    }

    @Provides
    @GradeScope
    Student provideStudent(){

        return new Student();
    }
}
