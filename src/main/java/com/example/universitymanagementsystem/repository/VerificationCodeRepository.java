package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.applyment.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode,Long> {
//    Optional<VerificationCode> findByOwnerPersonalNumber(Long pn);

    Optional<VerificationCode> getByApplicantApplicationId(Long id);
}
