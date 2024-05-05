package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.request.ApplicantVerificationCodeDto;
import com.example.universitymanagementsystem.entity.applyment.VerificationCode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicantVerificationCodeMapper {
    VerificationCode dtoToEntity(ApplicantVerificationCodeDto applicantVerificationCodeDto);
}
