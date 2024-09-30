package com.codegym.minitestweek2.repository;

import com.codegym.minitestweek2.model.Classes;
import org.springframework.data.repository.CrudRepository;

public interface IClassRepository extends CrudRepository<Classes,Long> {
}
