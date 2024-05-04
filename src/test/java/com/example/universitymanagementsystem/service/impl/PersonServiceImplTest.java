package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.PersonData;
import com.example.universitymanagementsystem.exception.PersonNotFoundException;
import com.example.universitymanagementsystem.exception.PersonalNumberAlreadyExistException;
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



    /*
    TODO: UNIT TESTS PRACTICE

        @Test
        void addNewPerson_OK() throws PersonalNumberAlreadyExistException {
            PersonServiceImpl personService = new PersonServiceImpl();
            PersonData personData = new PersonData();
            Long l = personService.addNewPerson(personData);
            Assertions.assertNotNull(l);
        }

        @Test
        void addNewPerson_PnAlreadyExist(){
            PersonServiceImpl personService = new PersonServiceImpl();
            PersonData personData = new PersonData();
            PersonalNumberAlreadyExistException personalNumberAlreadyExistException = assertThrows(
                    PersonalNumberAlreadyExistException.class, () -> personService.addNewPerson(personData));
            Assertions.assertEquals(
                    PersonalNumberAlreadyExistException.class,personalNumberAlreadyExistException.getClass());
        }
     */
    @Test
    void findByPersonalNumber_PersonNotFoundException() throws PersonNotFoundException {
        Mockito.when(personRepository.findByPersonalNumber(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(PersonNotFoundException.class,() -> personService.findByPN(1L));
    }
    @Test
    void findByPn_OK() throws  PersonNotFoundException {
        PersonData expPerson = new PersonData();
        expPerson.setCity("Bishkek");
        expPerson.setEmail("test@gmail.com");
        expPerson.setFirstName("Test");
        expPerson.setPersonalNumber(1L);

        PersonData retPerson = new PersonData();
        retPerson.setCity("Bishkek");
        retPerson.setEmail("test@gmail.com");
        retPerson.setFirstName("Test");
        retPerson.setPersonalNumber(1L);
        Mockito.when(personRepository.findByPersonalNumber(1L)).thenReturn(Optional.of(retPerson));

        PersonData res = personService.findByPN(1L);
        Assertions.assertEquals(expPerson,res);
    }



}