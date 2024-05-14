package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(imports = {ApplicantApplication.class}, componentModel = "spring")
public interface ApplicantApplicationMapper {

    @Mappings({
        @Mapping(target = "applicantApplication", source = "app")
    })
    Candidate applicantApplicationToCandidate(ApplicantApplication app);
}
