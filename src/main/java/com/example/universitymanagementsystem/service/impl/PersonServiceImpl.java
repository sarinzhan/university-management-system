package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.PersonData;
import com.example.universitymanagementsystem.exception.PersonNotFoundException;
import com.example.universitymanagementsystem.repository.PersonRepository;
import com.example.universitymanagementsystem.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonData findByPN(Long pn) throws PersonNotFoundException {
        return personRepository
                .findById(pn)
                .orElseThrow(() -> new PersonNotFoundException("Person by " + pn + " PN not found"));

    }
}
