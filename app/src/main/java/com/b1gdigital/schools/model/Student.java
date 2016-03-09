package com.b1gdigital.schools.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.b1gdigital.schools.BR;

import javax.inject.Inject;

/**
 * Created by Ricardo on 29/02/2016.
 */
public class Student extends BaseObservable {

    private String name = "School name";
    private String grade;

    @Inject
    public Student() {

    }

    public String getName() {

        return name;
    }

    @Bindable
    public void setName(String name) {

        this.name = name;

        notifyPropertyChanged(BR.name);
    }

    public String getGrade() {

        return grade;
    }

    @Bindable
    public void setGrade(String grade) {

        this.grade = grade;

        notifyPropertyChanged(BR.name);
    }

    public void reset() {

        name = "";
        name = "";
    }
}
