package com.campusmaster.service;

import com.campusmaster.entity.Assignment;

import java.util.List;

public interface AssignmentService {
    Assignment create(Assignment assignment, Long courseId);
    List<Assignment> findByCourse(Long courseId);
}
