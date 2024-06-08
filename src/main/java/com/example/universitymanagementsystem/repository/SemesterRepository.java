package com.example.universitymanagementsystem.repository;

import com.example.universitymanagementsystem.entity.study.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester,Long> {

    @Query(value = "select s from semester s " +
            "where s.group.id = :groupId")
    List<Semester> getAllByGroupId(Long groupId);
}
