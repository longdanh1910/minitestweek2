package com.codegym.minitestweek2.service;

import com.codegym.minitestweek2.model.Classes;
import com.codegym.minitestweek2.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService extends IGenerateService<Student>{
    Iterable<Student> findAllByClass(Classes clazz);
    Page<Student>findAll(Pageable pageable);
    Page<Student> findAllByFirstNameContaining(Pageable pageable, String name);
}
