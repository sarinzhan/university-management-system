package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.Candidate;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class CandidateServiceImplTest {
    @InjectMocks
    private CandidateServiceImpl candidateService;
    @Mock
    private CandidateRepository candidateRepository;
    @Test
    void getAllActiveBySpecId_success() {
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        when(candidateRepository.findAllActiveBySpecId(anyLong())).thenReturn(List.of(candidate));
        List<Candidate> allActiveBySpecId = candidateService.getAllActiveBySpecId(1L);
        assertEquals(1,allActiveBySpecId.size());
        assertEquals(candidate.getId(),allActiveBySpecId.get(0).getId());
    }

    @Test
    void getAllActiveBySpecId__no_candidate(){
        when(candidateRepository.findAllActiveBySpecId(anyLong())).thenReturn(List.of());
        BaseBusinessLogicException exception = assertThrows(BaseBusinessLogicException.class, () -> candidateService.getAllActiveBySpecId(1L));
        assertEquals("Кандидатов по данному направлению отсутствуют",exception.getMessage());
    }
}