package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByPersonalNumber(Long pn);
}
