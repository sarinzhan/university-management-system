package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.Person;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.mapper.EntityToEntityMapper;
import com.example.universitymanagementsystem.repository.PersonRepository;
import com.example.universitymanagementsystem.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;


    @Override
    public Person findByPN(Long pn) {
        return personRepository
                .findByPersonalNumber(pn)
                .orElseThrow(() -> new BaseBusinessLogicException("Не удалось найти данные человека"));
    }

    @Override
    public Person addOrEdit(Person person){
        // TODO: not tested / checked
        personRepository
                .findByPersonalNumber(person.getPersonalNumber())
                .ifPresent(persistPerson -> person.setId(persistPerson.getId()));
        return personRepository.save(person);

    }
}
