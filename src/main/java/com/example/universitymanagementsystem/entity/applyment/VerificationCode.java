package com.example.universitymanagementsystem.entity.applyment;

import com.example.universitymanagementsystem.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "verification_code")
@Data
public class VerificationCode extends BaseEntity {
    @Column(unique = true,nullable = false)
    private String code;

    @Column(name = "expire_date")
    private LocalDateTime expireDate;

    @Column(name = "applicant_application_id")
    private Long applicantApplicationId;

    @Column(name = "is_applied")
    private Boolean isApplied;
}
