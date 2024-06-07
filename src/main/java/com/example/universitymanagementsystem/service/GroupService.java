package com.example.universitymanagementsystem.service;

import com.example.universitymanagementsystem.entity.uni_struct.Group;

import java.util.List;

public interface GroupService {
    Long save(Group group);

    List<Group> getAllBySpecialtyId(Long specialtyId);
}
