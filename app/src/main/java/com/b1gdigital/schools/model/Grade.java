package com.b1gdigital.schools.model;

import java.util.ArrayList;

import javax.inject.Inject;

public class Grade {

    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private String name;

    @Inject
    public Grade() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void addTeacher(Teacher teacher) {

        teachers.add(teacher);
    }

    public ArrayList getTeachers() {

        return teachers;
    }

    public void addStudent(Student student) {

        students.add(student);
    }

    public ArrayList getStudents() {

        return students;
    }

    public Student getStudent(int position) {

        return students.get(position);
    }
}
