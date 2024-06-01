package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.uni_struct.Faculty;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.FacultyRepository;
import com.example.universitymanagementsystem.service.FacultyService;
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
public class FacultyServiceImplTest {
    @InjectMocks
    private FacultyServiceImpl facultyService;

    @Mock
    private FacultyRepository facultyRepository;

    private Faculty expFaculty;

    @BeforeEach
    void setUp(){
        expFaculty = new Faculty();
        expFaculty.setId(1L);
    }

    @Test
    void getAllFaculties_no_Faculties(){
        when(facultyRepository.findAll()).thenReturn(List.of());
        BaseBusinessLogicException exception =
                assertThrows(BaseBusinessLogicException.class, () -> facultyService.getAllFaculties());

        assertEquals("Факультеты не найдены", exception.getMessage());
    }

    @Test
    void getAllFaculties_success(){
        List<Faculty> expFaculties = List.of(expFaculty);
        when(facultyRepository.findAll()).thenReturn(expFaculties);

        List<Faculty> retFaculties = facultyService.getAllFaculties();

        assertNotNull(expFaculties);
        assertEquals(1, expFaculties.size());
        assertEquals(expFaculty, retFaculties.get(0));
    }
}
