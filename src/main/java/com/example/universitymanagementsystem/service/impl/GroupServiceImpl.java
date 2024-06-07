package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.entity.uni_struct.Group;
import com.example.universitymanagementsystem.exception.BaseBusinessLogicException;
import com.example.universitymanagementsystem.repository.GroupRepository;
import com.example.universitymanagementsystem.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public Long save(Group group) {
        return groupRepository.save(group).getId();
    }

    @Override
    public List<Group> getAllBySpecialtyId(Long specialtyId) {
        List<Group> groupList = groupRepository.getAllBySpecialtyId(specialtyId);
        if(groupList.isEmpty()){
            throw new BaseBusinessLogicException("Не удалось найти группы по специальности");
        }
        return groupList;
    }
}
