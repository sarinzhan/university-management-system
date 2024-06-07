package com.example.universitymanagementsystem.controller;

import com.example.universitymanagementsystem.dto.response.CommonResponseDto;
import com.example.universitymanagementsystem.dto.response.GroupResponseDto;
import com.example.universitymanagementsystem.mapper.GroupResponseMapper;
import com.example.universitymanagementsystem.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group")
@Tag(name = "Group APIs")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    private final GroupResponseMapper groupResponseMapper;

    @GetMapping("/get-all-by-specialty-id/{specialtyId}")
    @Operation(summary = "Get groups by specialty")
    @PermitAll
    public CommonResponseDto<List<GroupResponseDto>> getAllBySpecialtyId(
            @PathVariable Long specialtyId
    ) {
        return new CommonResponseDto<List<GroupResponseDto>>()
                .setOk()
                .setData(
                        groupResponseMapper.listEntityToDto(
                                groupService.getAllBySpecialtyId(specialtyId)
                        )
                );
    }
}
