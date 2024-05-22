package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.Person;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void findByPersonalNumber_PersonNotFoundException() {
        Mockito.when(personRepository.findByPersonalNumber(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(BaseBusinessLogicException.class,() -> personService.findByPN(1L));
    }
    @Test
    void findByPn_OK() {
        Person expPerson = new Person();
        expPerson.setCity("Bishkek");
        expPerson.setEmail("test@gmail.com");
        expPerson.setFirstName("Test");
        expPerson.setPersonalNumber(1L);

        Person retPerson = new Person();
        retPerson.setCity("Bishkek");
        retPerson.setEmail("test@gmail.com");
        retPerson.setFirstName("Test");
        retPerson.setPersonalNumber(1L);
        Mockito.when(personRepository.findByPersonalNumber(1L)).thenReturn(Optional.of(retPerson));

        Person res = personService.findByPN(1L);
        Assertions.assertEquals(expPerson,res);
    }



}