package com.example.universitymanagementsystem.entity.uni_struct;

import com.example.universitymanagementsystem.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import org.mapstruct.Mapping;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specialty extends BaseEntity {
    private String name;
    private String degree;
    private String edu_form;

    public Specialty(Long id,String name) {
        super(id);
        this.name = name;
    }
}
