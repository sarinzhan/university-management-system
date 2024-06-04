package com.example.universitymanagementsystem.entity.uni_struct;

import com.example.universitymanagementsystem.entity.BaseEntity;
import com.example.universitymanagementsystem.entity.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity(name = "report")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Report extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @Column(name = "link")
    private String link;

    @Column(name = "scope")
    private String scope;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "assignment_date")
    private LocalDateTime assignmentDate;
}
