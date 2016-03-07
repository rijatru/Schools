package com.b1gdigital.schools.models;

import javax.inject.Inject;

/**
 * Created by Ricardo on 29/02/2016.
 */
public class Teacher {

    String name;
    int grade;

    @Inject
    public Teacher() {

    }

    public void setName(String name) {

        this.name = name;
    }

    public void setGrade(int grade) {

        this.grade = grade;
    }
}
