package com.campusmaster.service;

import com.campusmaster.entity.Course;

import java.util.List;

public interface CourseService {
    Course create(Course course);
    List<Course> findAll();
    Course findById(Long id);
    void delete(Long id);
}
