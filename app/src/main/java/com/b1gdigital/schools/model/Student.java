package com.b1gdigital.schools.model;

import android.util.Log;

import javax.inject.Inject;

public class Student {

    private String name = "School name";
    private int grade;

    @Inject
    public Student() {

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

    public void onTextChanged(CharSequence s, int start, int before, int count) {

        Log.w("tag", "Student onTextChanged " + s);
    }
}
