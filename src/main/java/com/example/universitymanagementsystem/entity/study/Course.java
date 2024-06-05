package com.example.universitymanagementsystem.entity.study;

import com.example.universitymanagementsystem.entity.BaseEntity;
import com.example.universitymanagementsystem.entity.uni_struct.Teacher;
import com.example.universitymanagementsystem.entity.uni_struct.Group;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Course extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "curriculum_discipline_id", nullable = false)
    private CurriculumDiscipline curriculumDiscipline;

    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
}
