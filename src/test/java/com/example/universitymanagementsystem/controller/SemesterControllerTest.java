package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.BaseIT;
import com.example.universitymanagementsystem.entity.study.Semester;
import com.example.universitymanagementsystem.repository.SemesterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class SemesterControllerTest extends BaseIT {

    @MockBean
    private SemesterRepository semesterRepository;

    private List<Semester> semesterList;

    @BeforeEach
    void init(){
        semesterList = new ArrayList<>();
        Semester semester1 = new Semester();
        semester1.setId(1L);
        semester1.setNumber(1);
        semesterList.add(semester1);
        Semester semester2 = new Semester();
        semester2.setId(2L);
        semester2.setNumber(2);
        semesterList.add(semester2);
    }
    @Test
    @WithMockUser()
    void getAllByGroupId_OK() throws Exception {
        when(semesterRepository.getAllByGroupId(anyLong())).thenReturn(semesterList);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/semester/get-all-by-group-id/{groupId}",1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].semesterId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].number").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].semesterId").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[1].number").value(2));

    }
    @Test
    @WithMockUser()
    void getAllByGroupId_no_data() throws Exception {
        when(semesterRepository.getAllByGroupId(anyLong())).thenReturn(List.of());
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/semester/get-all-by-group-id/{groupId}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Не удалось найти семестры по специальности"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400));

    }
}