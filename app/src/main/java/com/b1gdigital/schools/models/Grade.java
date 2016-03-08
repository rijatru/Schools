package com.b1gdigital.schools.models;

import javax.inject.Inject;

public class Grade {

    String name;
    int grade;
    private Teacher teacher;

    @Inject
    public Grade(Teacher teacher) {

        this.teacher = teacher;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setGrade(int grade) {

        this.grade = grade;
    }

    public Teacher getTeacher() {

        return teacher;
    }
}
