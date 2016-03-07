package com.b1gdigital.schools.workers;

import com.b1gdigital.schools.models.Student;

import java.util.Random;

import javax.inject.Inject;

/**
 * Created by Ricardo on 29/02/2016.
 */
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
