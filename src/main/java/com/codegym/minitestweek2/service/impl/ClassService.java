package com.codegym.minitestweek2.service.impl;

import com.codegym.minitestweek2.model.Classes;
import com.codegym.minitestweek2.repository.IClassRepository;
import com.codegym.minitestweek2.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClassService implements IClassService {

    @Autowired
    private IClassRepository iClassRepository;

    @Override
    public Iterable<Classes> findAll(){
        return iClassRepository.findAll();
    }

    @Override
    public void save(Classes classes){
        iClassRepository.save(classes);
    }

    @Override
    public void remove(Long id){
        iClassRepository.deleteById(id);
    }

    @Override
    public Optional<Classes> findById(Long id){
        return iClassRepository.findById(id);
    }

}
