package com.campusmaster.service.impl;

import com.campusmaster.entity.Assignment;
import com.campusmaster.entity.Course;
import com.campusmaster.repository.AssignmentRepository;
import com.campusmaster.repository.CourseRepository;
import com.campusmaster.service.AssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository,
                                 CourseRepository courseRepository) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Assignment create(Assignment assignment, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        assignment.setCourse(course);
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<Assignment> findByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return assignmentRepository.findByCourse(course);
    }
}
