package com.example.universitymanagementsystem.entity;

import com.example.universitymanagementsystem.entity.uni_struct.Department;
import com.example.universitymanagementsystem.entity.uni_struct.Discipline;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Teacher extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "employment_date")
    private LocalDateTime employmentDate;

    @Column(name = "termination_date")
    private LocalDateTime terminationDate;

    @ManyToMany
    @JoinTable(
            name = "m2m_discipline_teacher",
        joinColumns = @JoinColumn(name = "teacher_id"),
        inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private Set<Discipline> disciplines;
}