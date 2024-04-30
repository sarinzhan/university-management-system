package com.example.universitymanagementsystem.entity.uni_struct;

import com.example.universitymanagementsystem.entity.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity(name = "Faculty")
@Data
public class Faculty extends BaseEntity {
    private String name;
}
