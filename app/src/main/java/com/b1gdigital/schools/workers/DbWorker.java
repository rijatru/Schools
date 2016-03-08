package com.b1gdigital.schools.workers;

import com.b1gdigital.schools.model.Student;

import java.util.Random;

import javax.inject.Inject;

public class DbWorker {

    @Inject
    public DbWorker() {

    }

    public void createStudents() {

        Random randomGenerator = new Random();

        for (int i = 0; i < 1; i++) {

            Student student = new Student();
            student.setName("Ricardo_" + i);
            student.setGrade(randomGenerator.nextInt(100));
        }
    }
}
