package com.codegym.minitestweek2.service;

import java.util.Optional;

public interface IGenerateService <T>{
    Iterable<T> findAll();
    void save(T t);
    void remove(Long id);
    Optional<T> findById(Long id);
}
