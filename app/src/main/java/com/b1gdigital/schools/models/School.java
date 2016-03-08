package com.b1gdigital.schools.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.b1gdigital.schools.BR;

import java.util.ArrayList;

import javax.inject.Inject;

public class School extends BaseObservable {

    Grade grade;
    Teacher teacher;
    ArrayList<Student> students = new ArrayList<>();
    private String name = "Init name";

    @Inject
    public School(Grade grade) {

        this.grade = grade;
        this.teacher = grade.getTeacher();

        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getName() {

        return this.name;
    }

    public void setName(String name) {

        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
