package com.dauflo.dao;

import com.dauflo.entity.Student;

import java.util.List;

public interface StudentDao {
    void create(Student student);

    List<Student> getStudents();

    boolean update(Student student);

    boolean delete(Student student);
}
