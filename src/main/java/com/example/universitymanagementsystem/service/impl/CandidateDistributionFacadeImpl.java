package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.dto.response.CandidateDistributionResponseDto;
import com.example.universitymanagementsystem.entity.Person;
import com.example.universitymanagementsystem.entity.uni_struct.Student;
import com.example.universitymanagementsystem.entity.User;
import com.example.universitymanagementsystem.entity.applyment.Candidate;
import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import com.example.universitymanagementsystem.entity.uni_struct.Group;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.mapper.CandidateDistributionResponseMapper;
import com.example.universitymanagementsystem.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CandidateDistributionFacadeImpl implements CandidateDistributionService {
    private final CandidateService candidateService;
    private final ApplicantApplicationService applicantApplicationService;
    private final SpecialtyAdmissionService specialtyAdmissionService;
    private final PersonService personService;
    private final GroupService groupService;
    private final StudentService studentService;
    private final UserRoleService userRoleService;
    private final UserService userService;
    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;


    private final CandidateDistributionResponseMapper candidateDistributionResponseMapper;

    @Transactional 
    public List<CandidateDistributionResponseDto> distributeCandidates(Long admissionId){

        SpecialtyAdmission admission = specialtyAdmissionService.getById(admissionId);
        if(admission.getEndDate().isAfter(LocalDateTime.now())){
            throw new BaseBusinessLogicException("Невозможно начать распределение, набор еще не закончен");
        }
        if(admission.getIsDistributed()){
            throw new BaseBusinessLogicException("Кандидаты данного набора уже распределены");
        }
        //check if applicant application is empty
        applicantApplicationService.getByAdmissionId(admissionId)
                .stream()
                .filter(x -> x.getIsChecked().equals(false))
                .findFirst()
                .ifPresent(x -> {throw new BaseBusinessLogicException("Невозможно распределить кандидатов.Заявки абитуриентов ожидают проверки. Необходимо проверить!");});

        // remove lower test score than required
        // sort by test score
        // remove extra
        List<Candidate> candidateList = candidateService.getAllByAdmissionId(admissionId)
                .stream()
                .filter(x -> admission.getMinScore() <= x.getTestScore())
                .sorted(Comparator.comparing(Candidate::getTestScore).reversed())
                .limit((long) admission.getGroupAmount() * admission.getGroupCapacity())
                .toList();
        if(candidateList.isEmpty()){
            throw new BaseBusinessLogicException("Невозможно распределить кандидатов.Кандидаты отсутствуют");
        }
        //divide candidates by test score balanced
        //create required amount of group
        List<List<Candidate>> dividedCandidates = divide(candidateList, admission.getGroupAmount());
        List<CandidateDistributionResponseDto> distributedStudents = new ArrayList<>();
        for(int i = 0; i < dividedCandidates.size(); i++){
            Group group = createGroup(admission, i+1);
            for(int j = 0; j < dividedCandidates.get(i).size(); j++){
                Candidate currCandidate = dividedCandidates.get(i).get(j);
                User user = createUser(currCandidate);
                Student student = createStudent(currCandidate,group, user);
                distributedStudents.add(candidateDistributionResponseMapper.entityToDto(student));
            }
        }
        admission.setIsDistributed(true);
        return distributedStudents;
    }

    private Group createGroup(SpecialtyAdmission admission, Integer number){
        Group group = new Group();
        String abbreviation = admission.getSpecialty().getGroupAbbreviation();
        String groupName = abbreviation + "-"+ number + "-" + LocalDate.now().getYear();
        group.setName(groupName);
        group.setSpecialty(admission.getSpecialty());
        return group;
    }

    private Student createStudent(Candidate candidate, Group group,User user){
        Student student = new Student();
        student.setGroup(group);
        student.setApplication(candidate.getApplicantApplication());
        Person person = personService.findByPN(candidate.getApplicantApplication().getPersonalNumber());
        student.setPerson(person);
        student.setEnrollmentDate(LocalDateTime.now());
        student.setIsStudying(true);
        student.setIsExpelled(false);
        student.setUser(user);
        return student;
    }

    private List<List<Candidate>> divide(List<Candidate> candidateList,Integer groupAmount){
        List<List<Candidate>> resultList = new ArrayList<>();
        for(int i = 0; i < groupAmount; i++){
            List<Candidate> temp = new ArrayList<>();
            for(int j = i; j < candidateList.size(); j += groupAmount){
                temp.add( candidateList.get(i));
            }
            resultList.add(temp);
        }
        return resultList;
    }

    private User createUser(Candidate candidate){
        User user = new User();
        user.setUserRole(userRoleService.findByName("STUDENT"));
        Person person = personService.findByPN(candidate.getApplicantApplication().getPersonalNumber());
        user.setPerson(person);
        user.setIsActive(true);
        user.setLogin(person.getFirstName());
        String password = Integer.toString(new Random().nextInt(100000, 999999));
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userService.save(user);
        emailService.sendMessage(person.getEmail(), "Университет", generateMessage(candidate,password));
        return userService.save(user);
    }

    private String generateMessage(Candidate candidate,String password){
        return  "Мы рады сообщить Вам, что Вы были успешно зачислены на направление %s !\n".formatted(candidate.getSpecialtyAdmission().getSpecialty().getName()) +
                "Ваш данные для входа в ИАИС\n" +
                "Login: %s\nPassword:%s".formatted(candidate.getApplicantApplication().getFirstName(),password);
    }
}
