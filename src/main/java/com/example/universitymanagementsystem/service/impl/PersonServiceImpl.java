package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.PersonData;
import com.example.universitymanagementsystem.exception.PersonNotFoundException;
import com.example.universitymanagementsystem.exception.PersonalNumberAlreadyExistException;
import com.example.universitymanagementsystem.repository.PersonRepository;
import com.example.universitymanagementsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;


    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonData findByPN(Long pn) throws PersonNotFoundException {
        return personRepository
                .findByPersonalNumber(pn)
                .orElseThrow(() -> new PersonNotFoundException("Person by " + pn + " PN not found"));

    }

    @Override
    public Long addNewPerson(PersonData personData) throws PersonalNumberAlreadyExistException {
        personRepository
                .findByPersonalNumber(personData.getPersonalNumber())
                .orElseThrow(() -> new PersonalNumberAlreadyExistException("Personal number %d already exist".formatted(personData.getPersonalNumber())));
        return personRepository.save(personData).getId();
    }
}
