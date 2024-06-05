package com.example.universitymanagementsystem.entity.study;

import com.example.universitymanagementsystem.entity.BaseEntity;
import com.example.universitymanagementsystem.entity.uni_struct.Student;
import com.example.universitymanagementsystem.entity.uni_struct.Teacher;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity(name = "intermediate_result")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class IntermediateResult extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column(name = "grading_date")
    private LocalDateTime gradingDate;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "score")
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum;
}
