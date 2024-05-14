package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.ApplicantApplication;
import com.example.universitymanagementsystem.entity.applyment.VerificationCode;
import com.example.universitymanagementsystem.exception.*;
import com.example.universitymanagementsystem.repository.ApplicantApplicationRepository;
import com.example.universitymanagementsystem.repository.CandidateRepository;
import com.example.universitymanagementsystem.repository.SpecialtyAdmissionRepository;
import com.example.universitymanagementsystem.repository.VerificationCodeRepository;
import com.example.universitymanagementsystem.service.ApplicantApplicationService;
import com.example.universitymanagementsystem.service.EmailService;
import com.example.universitymanagementsystem.service.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicantApplicationServiceImpl implements ApplicantApplicationService {
    private final CandidateRepository candidateRepository;
    private final ApplicantApplicationRepository applicantApplicationRepository;
    private final SpecialtyAdmissionRepository specialtyAdmissionRepository;
    private final VerificationCodeService verificationCodeService;
    private final EmailService emailService;

    /**
     * If applicant by specified PN already waiting for verify it will throw exception.
     * If applicant is candidate already it will throw exception
     * If chosen specialty admission is not available it will throw exception
     * Sent code to specified email to verify email.
     * @param app
     * @return Long - created applicant application id
     */
    @Override
    public Long registerApplicantApplication(ApplicantApplication app){
        if(applicantApplicationRepository.findByPnWhichIsNonChecked(app.getPersonalNumber())
                .isPresent()){
            throw new BaseBusinessLogicException("Ваше заявление ожидает проверки");
        }
        candidateRepository.findActiveByPn(app.getPersonalNumber())
                .ifPresent(x -> {
                    throw new BaseBusinessLogicException("Вы уже числитесь кандидатом по направлению " + x.getApplicantApplication().getSpecialty().getName());});

                specialtyAdmissionRepository
                .getActiveBySpecId(app.getSpecialty().getId())
                .orElseThrow(() -> new BaseBusinessLogicException("Набор по выбранному направлению не активен"));
        ApplicantApplication applicantApplication = applicantApplicationRepository.save(app);

        VerificationCode verificationCode = verificationCodeService.generateCode(applicantApplication.getId());
        String subject = "Подтверждение адреса электронной почты и активация кандидатуры";
        String message = generateText(verificationCode.getCode(),applicantApplication);
        emailService.sendMessage(app.getEmail(), subject, message);
        return applicantApplicationRepository.save(app).getId();
    }

    @Override
    public ApplicantApplication verificationOfApplicantApplication(Long id, String message, boolean verify) {
        if (id == 0) {
            throw new BaseBusinessLogicException("Id заявителя не может равняться 0!");
        }

        if (this.applicantApplicationRepository.getAllNonCheckedActivated().isEmpty()) {
            throw new BaseBusinessLogicException("Не проверенных заявителей нет!");
        }

        if (this.applicantApplicationRepository.findById(id).isEmpty()) {
            throw new BaseBusinessLogicException("Не найдено заявителя с id: " + id);
        }

        ApplicantApplication applicantApplication = this.applicantApplicationRepository.findById(id).get();

        if (verify) {
            applicantApplication.setIsDeclined(false);
            applicantApplication.setIsChecked(true);
            applicantApplication.setIsAccepted(true);

            message = checkingMessage(message, true, applicantApplication.getFirstName());

            this.emailService.sendMessage(applicantApplication.getEmail(), "Рассмотрение заявки на поступление", message);
        } else {
            applicantApplication.setIsDeclined(true);
            applicantApplication.setIsChecked(true);
            applicantApplication.setIsAccepted(false);

            message = checkingMessage(message, false, applicantApplication.getFirstName());

            this.emailService.sendMessage(applicantApplication.getEmail(), "Рассмотрение заявки на поступление", message);
        }

        return applicantApplication;
    }

    private String generateText(String code,ApplicantApplication applicantApplication){
        return " \n" +
                applicantApplication.getFirstName() +", здравствуйте! \n" +
                " \n" +
                "Благодарим вас за регистрацию на набор по направлению "+ applicantApplication.getSpecialty().getName() + " в Кыргызско-Российско Славянском Университете. Для активации вашей кандидатуры и начала процесса отбора, пожалуйста, используйте следующий код активации: \n" +
                " \n" +
                "Код активации: "+ code + "\n" +
                " \n" +
                "После подтверждения кода, ваша заявка будет отправлена на проверку. Мы внимательно изучим предоставленные данные и свяжемся с вами с результатами проверки.\n" +
                " \n" +
                "Если вы не регистрировались в данной программе и получили это письмо по ошибке, пожалуйста, проигнорируйте его. \n" +
                " \n" +
                "С уважением, \n" +
                "Приемная комиссия КРСУ";
    }

    private String checkingMessage(String message, boolean verify, String nameOfApplicant) {
        if (verify && (Objects.isNull(message) || message.trim().isEmpty())) {
            return nameOfApplicant + ", здравствуйте! \n" +
                    " \n" +
                    "Поздравляем!" + "\n" +
                    " \n" +
                    "Вы были рекомендованы к зачислению!";
        } else {
            return nameOfApplicant + ", здравствуйте! \n" +
                    " \n" +
                    "Извините, вы не были рекомендованы к зачислению!";
        }
    }
}
