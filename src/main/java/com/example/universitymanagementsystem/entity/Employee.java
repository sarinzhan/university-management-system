package com.example.universitymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "employee")
@Data
public class Employee extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "person_data_id")
    private PersonData personData;

    @Column(name = "retire_date")
    private LocalDateTime retireDate;

    @Column(name = "isworking")
    private Boolean isWorking;

    @Column(name = "take_date")
    private LocalDateTime takeDate;

    @ManyToOne
    @JoinColumn(name = "employee_role_id")
    private EmployeeRole role;

}
