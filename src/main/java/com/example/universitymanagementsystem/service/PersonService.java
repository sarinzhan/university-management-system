package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.Person;

public interface PersonService {
    Person findByPN(Long pn);
    Person addOrEdit(Person person);
}
