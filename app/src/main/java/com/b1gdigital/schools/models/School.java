package com.b1gdigital.schools.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.b1gdigital.schools.BR;

import java.util.ArrayList;

import javax.inject.Inject;

public class School extends BaseObservable {

    private String name = "Init name";
    ArrayList<Grade> grades = new ArrayList<>();
    ArrayList<Teacher> teachers = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();

    @Inject
    public School() {

        notifyPropertyChanged(BR.name);
        notifyPropertyChanged(BR.name);
    }

    public void setName(String name) {

        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getName() {

        return this.name;
    }

    public ArrayList getGrades() {

        return this.grades;
    }
}
