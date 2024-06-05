package com.example.universitymanagementsystem.entity.study;

import com.example.universitymanagementsystem.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "curriculum_discipline")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CurriculumDiscipline extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum;

    @Column(name = "hour")
    private Integer hour;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;
}
