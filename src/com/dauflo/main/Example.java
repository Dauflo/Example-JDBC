package com.dauflo.main;

import com.dauflo.dao.JdbcStudentDao;
import com.dauflo.entity.Student;

import java.util.List;

public class Example {
    public static void main(String[] args) {

        JdbcStudentDao jdbcStudentDao = new JdbcStudentDao();

        // INSERT
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");

        jdbcStudentDao.create(student);

        // READ
        List<Student> students = jdbcStudentDao.getStudents();
        for (Student s : students) {
            System.out.println(s.getFirstName());
        }

        // UPDATE
        student = students.get(0);
        student.setFirstName("Bob");
        jdbcStudentDao.update(student);

        // DELETE
        jdbcStudentDao.delete(student);
    }
}
