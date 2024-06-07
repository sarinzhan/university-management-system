package com.example.universitymanagementsystem.mapper;

import com.example.universitymanagementsystem.dto.response.GroupResponseDto;
import com.example.universitymanagementsystem.entity.uni_struct.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupResponseMapper {

    @Mappings({
            @Mapping(target = "groupName", source = "name"),
            @Mapping(target = "specialtyName", source = "group.specialty.name"),
            @Mapping(target = "departmentName", source = "specialty.department.name"),
            @Mapping(target = "departmentId", source = "specialty.department.id"),
            @Mapping(target = "groupId", source = "id")
    })
    GroupResponseDto entityToDto(Group group);

    List<GroupResponseDto> listEntityToDto(List<Group> groupList);
}
