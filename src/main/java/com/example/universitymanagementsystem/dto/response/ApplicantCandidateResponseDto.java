package com.example.universitymanagementsystem.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.text.StyledEditorKit;

@Data
@AllArgsConstructor
@Schema(description = "Information about candidate")
public class ApplicantCandidateResponseDto {
    private String firstName;
    private String secondName;
    private String lastName;
    private Integer age;
    private Integer testScope;
    private Boolean isEmailActivated;
    private Boolean isChecked;
    private Boolean isRecommended;
}
