package com.campusmaster.controller;

import com.campusmaster.entity.Assignment;
import com.campusmaster.service.AssignmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@Tag(name = "Assignments")
@CrossOrigin
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<Assignment> create(@RequestBody Assignment assignment,
                                             @PathVariable Long courseId) {
        return ResponseEntity.ok(assignmentService.create(assignment, courseId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Assignment>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(assignmentService.findByCourse(courseId));
    }
}
