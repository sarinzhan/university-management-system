package com.example.universitymanagementsystem.entity.study;

import com.example.universitymanagementsystem.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "discipline")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Discipline extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;
}
