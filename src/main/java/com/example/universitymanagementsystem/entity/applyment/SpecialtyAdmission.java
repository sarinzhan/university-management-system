package com.example.universitymanagementsystem.entity.applyment;

import com.example.universitymanagementsystem.entity.BaseEntity;
import com.example.universitymanagementsystem.entity.uni_struct.Department;
import com.example.universitymanagementsystem.entity.uni_struct.Faculty;
import com.example.universitymanagementsystem.entity.uni_struct.Specialty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "specialty_admission")
@Data
public class SpecialtyAdmission extends BaseEntity {
    @Column(name = "start_date",nullable = false)
    @Future(message = "Не может быть в прошлом")
    private LocalDateTime startDate;

    @Column(name ="end_date",nullable = false)
    @Future(message = "Не может быть в прошлом")
    private LocalDateTime endDate;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "group_capacity",nullable = false)
    private Integer groupCapacity;

    @Column(name = "group_amount",nullable = false)
    private Integer groupAmount;

    @Column(name = "min_score", nullable = false)
    @Positive(message = "Не может быть отрицательным")
    @Max(value = 400)
    private Integer minScore;

    @ManyToOne
    @JoinColumn(name = "specialty_id",nullable = false)
    private Specialty specialty;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
