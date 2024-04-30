package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.request.RegisterApplicantApplicationDto;
import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import org.mapstruct.Mapper;

@Mapper
public interface RegisterApplicantApplicationMapper {
    ApplicantApplication dtoToEntity(RegisterApplicantApplicationDto dto);
}
