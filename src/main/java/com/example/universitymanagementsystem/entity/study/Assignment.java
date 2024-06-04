package com.example.universitymanagementsystem.entity.study;

import com.example.universitymanagementsystem.entity.BaseEntity;
import com.example.universitymanagementsystem.entity.study.Course;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity(name = "assignment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Assignment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "description")
    private String description;

    @Column(name = "subject")
    private String subject;

    @Column(name = "max_scope")
    private int maxScope;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "assignment_date")
    private LocalDateTime assignmentDate;
}
