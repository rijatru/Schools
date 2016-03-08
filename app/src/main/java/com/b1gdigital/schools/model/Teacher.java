package com.b1gdigital.schools.model;

import javax.inject.Inject;

public class Teacher {

    private String name;
    private int grade;

    @Inject
    public Teacher() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getGrade() {

        return grade;
    }

    public void setGrade(int grade) {

        this.grade = grade;
    }
}
