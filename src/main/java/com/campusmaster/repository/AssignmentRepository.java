package com.campusmaster.repository;

import com.campusmaster.entity.Assignment;
import com.campusmaster.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByCourse(Course course);
}
