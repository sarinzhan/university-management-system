package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.PersonData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonData,Long> {

}
