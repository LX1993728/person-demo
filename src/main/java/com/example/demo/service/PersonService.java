package com.example.demo.service;

import com.example.demo.entities.PersonEntity;

import java.util.List;

public interface PersonService {
    List<PersonEntity> getPersonList();

    void savePerson(PersonEntity person);

    PersonEntity findPersonById(Long id);
}
