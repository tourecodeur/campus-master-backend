package com.campusmaster.repository;

import com.campusmaster.entity.Course;
import com.campusmaster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacher(User teacher);
}
