package com.example.universitymanagementsystem;

import com.example.universitymanagementsystem.entity.PersonData;
import com.example.universitymanagementsystem.repository.PersonRepository;
import com.example.universitymanagementsystem.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private PersonRepository personRepository;
    @Test
    void findUserById_OK(){
        PersonData personData = new PersonData();
        personData.setCity("Bish");
        personData.setId(1L);

        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(personData));
    }
}
