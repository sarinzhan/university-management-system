package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.BaseIT;
import com.example.universitymanagementsystem.entity.uni_struct.Group;
import com.example.universitymanagementsystem.repository.GroupRepository;
import com.example.universitymanagementsystem.service.GroupService;
import com.example.universitymanagementsystem.service.impl.GroupServiceImpl;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class GroupControllerTest extends BaseIT {

//    @Mock
//    private GroupService groupService;

    @MockBean
    private GroupRepository groupRepository;

    private List<Group> groupList = new ArrayList<>();
    @BeforeEach
    void setGroupList(){
        Group group1 = new Group();
        Group group2 = new Group();
        group1.setId(1L);
        group1.setName("ЕПИ-1-21");
        group2.setId(2L);
        group2.setName("ЕПИ-2-21");
        groupList.add(group1);
        groupList.add(group2);
    }

    @Test
    @WithMockUser(roles = "ADMISSION")
    void getAllBySpecialtyId_OK() throws Exception {
//        when(groupService.getAllBySpecialtyId(anyLong())).thenReturn(groupList);
        when(groupRepository.getAllBySpecialtyId(anyLong())).thenReturn(groupList);
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/group/get-all-by-specialty-id/{specialtyId}",1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data[0].groupId").value(1L))
                .andExpect(jsonPath("$.data[0].groupName").value("ЕПИ-1-21"))
                .andExpect(jsonPath("$.data[1].groupId").value(2L))
                .andExpect(jsonPath("$.data[1].groupName").value("ЕПИ-2-21"))
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @Test
    @WithMockUser(roles = "ADMISSION")
    void getAllBySpecialtyId_no_data() throws Exception {
        when(groupRepository.getAllBySpecialtyId(anyLong())).thenReturn(List.of());
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/group/get-all-by-specialty-id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.message").value("Не удалось найти группы по специальности"))
                .andExpect(jsonPath("$.status").value(400));
    }
}