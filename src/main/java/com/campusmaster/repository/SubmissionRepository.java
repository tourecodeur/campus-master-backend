package com.campusmaster.repository;

import com.campusmaster.entity.Assignment;
import com.campusmaster.entity.Submission;
import com.campusmaster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignmentAndStudent(Assignment assignment, User student);
}
