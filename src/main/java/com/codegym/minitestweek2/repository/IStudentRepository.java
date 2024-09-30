package com.codegym.minitestweek2.repository;

import com.codegym.minitestweek2.model.Classes;
import com.codegym.minitestweek2.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface IStudentRepository extends CrudRepository<Student,Long> {
   Iterable<Student> findAllByClass(Classes classes);
   Page<Student>findAll(Pageable pageable);
   Page<Student> findAllByFirstNameContaining(Pageable pageable, String name);
}
