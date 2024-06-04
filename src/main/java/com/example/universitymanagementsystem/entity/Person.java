package com.example.universitymanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "person_data")
@Data
public class Person extends BaseEntity {
    @Column(name = "personal_number",unique = true,nullable = false)
    private Long personalNumber;

    private String email;

    private String nationality;

    @Column(name = "passport_id")
    private String passportId;

    private String country;

    private String city;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    private String gender;
}
