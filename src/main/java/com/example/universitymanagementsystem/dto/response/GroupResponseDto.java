package com.example.universitymanagementsystem.dto.response;

import lombok.Data;

@Data
public class GroupResponseDto {
    private Long groupId;
    private String groupName;
    private Long departmentId;
    private String departmentName;
    private String specialtyName;
}
