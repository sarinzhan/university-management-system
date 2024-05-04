package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.PersonData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonData,Long> {
    Optional<PersonData> findByPersonalNumber(Long pn);
}
