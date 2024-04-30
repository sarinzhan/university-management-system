package com.example.universitymanagementsystem.entity.uni_struct;

import com.example.universitymanagementsystem.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Group extends BaseEntity {
    private String name;
}
