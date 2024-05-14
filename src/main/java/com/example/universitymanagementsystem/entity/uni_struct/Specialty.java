package com.example.universitymanagementsystem.entity.uni_struct;

import com.example.universitymanagementsystem.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.mapstruct.Mapping;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Specialty extends BaseEntity {
    private String name;
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public Specialty(Long id,String name) {
        super(id);
        this.name = name;
    }
}
