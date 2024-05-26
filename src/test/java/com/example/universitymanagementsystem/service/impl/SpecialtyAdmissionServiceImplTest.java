package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.applyment.SpecialtyAdmission;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.SpecialtyAdmissionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.prefs.BackingStoreException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpecialtyAdmissionServiceImplTest {
    @InjectMocks
    private SpecialtyAdmissionServiceImpl specialtyAdmissionService;

    @Mock
    private SpecialtyAdmissionRepository specialtyAdmissionRepository;

    private SpecialtyAdmission expSpecialtySubmission;
    private SpecialtyAdmission retSpecialtyAdmission;

    @BeforeEach
    void setUp() {
        var startDate = LocalDateTime.of(2019, 8, 1, 0, 0);
        var endDate = LocalDateTime.of(2019, 9, 1, 0, 0);

        expSpecialtySubmission = new SpecialtyAdmission();
        expSpecialtySubmission.setId(1L);
        expSpecialtySubmission.setStartDate(startDate);
        expSpecialtySubmission.setEndDate(endDate);

        retSpecialtyAdmission = new SpecialtyAdmission();
        retSpecialtyAdmission.setId(1L);
        retSpecialtyAdmission.setStartDate(startDate);
        retSpecialtyAdmission.setEndDate(endDate);
    }

    @Test
    public void getActiveAdmissions_no_admissions(){
        when(specialtyAdmissionRepository.getAllActive()).thenReturn(List.of());
        BaseBusinessLogicException exception =
                assertThrows(BaseBusinessLogicException.class, () -> specialtyAdmissionService.getActiveAdmissions());

        assertEquals("Наборы не объявлены", exception.getMessage());
    }

    @Test
    public void getActiveAdmissions_success(){
        List<SpecialtyAdmission> expAdmissions = List.of(expSpecialtySubmission);
        when(specialtyAdmissionRepository.getAllActive()).thenReturn(expAdmissions);

        List<SpecialtyAdmission> retAdmissions = specialtyAdmissionService.getActiveAdmissions();

        assertNotNull(expAdmissions);
        assertEquals(1, expAdmissions.size());
        assertEquals(retAdmissions.get(0), expAdmissions.get(0));
    }

    @Test
    public void getActiveAdmissions_by_faculty_no_admissions(){
        when(specialtyAdmissionRepository.getAllActive(anyLong())).thenReturn(List.of());
        BaseBusinessLogicException exception =
                assertThrows(BaseBusinessLogicException.class, () -> specialtyAdmissionService.getActiveAdmissions(1L));

        assertEquals("Набор не объявлен", exception.getMessage());
    }

    @Test
    public void getActiveAdmissions_by_faculty_success(){
        List<SpecialtyAdmission> expAdmissions = List.of(expSpecialtySubmission);
        when(specialtyAdmissionRepository.getAllActive(anyLong())).thenReturn(expAdmissions);

        List<SpecialtyAdmission> retAdmissions = specialtyAdmissionService.getActiveAdmissions(1L);

        assertNotNull(expAdmissions);
        assertEquals(1, expAdmissions.size());
        assertEquals(retAdmissions.get(0), expAdmissions.get(0));
    }

    @Test
    public void getAllAdmissions_no_admissions(){
        when(specialtyAdmissionRepository.getAll()).thenReturn(List.of());
        BaseBusinessLogicException exception =
                assertThrows(BaseBusinessLogicException.class, () -> specialtyAdmissionService.getAllAdmissions());

        assertEquals("Наборов не объявлялось", exception.getMessage());
    }

    @Test
    public void getAllAdmissions_success(){
        List<SpecialtyAdmission> expAdmissions = List.of(expSpecialtySubmission);
        when(specialtyAdmissionRepository.getAll()).thenReturn(expAdmissions);

        List<SpecialtyAdmission> retAdmissions = specialtyAdmissionService.getAllAdmissions();

        assertNotNull(expAdmissions);
        assertEquals(1, expAdmissions.size());
        assertEquals(retAdmissions.get(0), expAdmissions.get(0));
    }

    @Test
    public void getAdmissionById_no_admission(){
        when(specialtyAdmissionRepository.getByAdmissionId(anyLong())).thenReturn(Optional.empty());
        BaseBusinessLogicException exception =
                assertThrows(BaseBusinessLogicException.class, () -> specialtyAdmissionService.getAdmissionById(1L));

        assertEquals("Набора с таким id не объявлялось", exception.getMessage());
    }

    @Test
    public void getAdmissionById_success(){
        when(specialtyAdmissionRepository.getByAdmissionId(anyLong())).thenReturn(Optional.of(retSpecialtyAdmission));

        SpecialtyAdmission res = specialtyAdmissionService.getAdmissionById(1L);
        assertEquals(expSpecialtySubmission, res);
    }
}
