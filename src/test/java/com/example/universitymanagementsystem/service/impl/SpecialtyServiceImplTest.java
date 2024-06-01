package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.uni_struct.Specialty;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class SpecialtyServiceImplTest {
    @InjectMocks
    private SpecialtyServiceImpl specialtyService;

    @Mock
    private SpecialtyRepository specialtyRepository;

    private Specialty expSpecialty;

    @BeforeEach
    void setUp(){
        expSpecialty = new Specialty();
        expSpecialty.setId(1L);
    }

    @Test
    void getAllSpecialties_no_specialties(){
        when(specialtyRepository.findAll()).thenReturn(List.of());
        BaseBusinessLogicException exception =
                assertThrows(BaseBusinessLogicException.class, () -> specialtyService.getAll());

        assertEquals("Специальности не найдены", exception.getMessage());
    }

    @Test
    void getAllSpecialties_success(){
        List<Specialty> expSpecialties = List.of(expSpecialty);
        when(specialtyRepository.findAll()).thenReturn(expSpecialties);

        List<Specialty> retSpecialties = specialtyService.getAll();

        assertNotNull(retSpecialties);
        assertEquals(1, retSpecialties.size());
        assertEquals(expSpecialty, retSpecialties.get(0));
    }
}
