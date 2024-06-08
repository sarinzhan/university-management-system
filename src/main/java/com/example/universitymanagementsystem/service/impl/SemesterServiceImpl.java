package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.study.Semester;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.SemesterRepository;
import com.example.universitymanagementsystem.service.SemesterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterServiceImpl implements SemesterService {
    private final SemesterRepository semesterRepository;
    @Override
    public List<Semester> getAllByGroupId(Long groupId) {
        List<Semester> allByGroupId = semesterRepository.getAllByGroupId(groupId);
        if(allByGroupId.isEmpty()) {
            throw new BaseBusinessLogicException("Не удалось найти семестры по специальности");
        }
        return allByGroupId;
    }
}
