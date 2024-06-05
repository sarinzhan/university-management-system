package com.example.universitymanagementsystem.entity.uni_struct;

import com.example.universitymanagementsystem.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.mapstruct.Mapping;

@Entity(name = "specialty")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Specialty extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "degree")
    private String degree;

    @Column(name = "group_abbreviation", nullable = false)
    private String groupAbbreviation;

    public Specialty(Long id,String name) {
        super(id);
        this.name = name;
    }

    @Column(name = "edu_form")
    private String educationForm;
}