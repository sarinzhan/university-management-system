package com.example.universitymanagementsystem.entity.uni_struct;

import com.example.universitymanagementsystem.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity(name = "curriculum")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Curriculum extends BaseEntity {
    @Column(name = "semester_number")
    private Integer semesterNumber;

    @ManyToOne
    @JoinColumn(name = "specialty", nullable = false)
    private Specialty specialty;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "name")
    private String name;
}
