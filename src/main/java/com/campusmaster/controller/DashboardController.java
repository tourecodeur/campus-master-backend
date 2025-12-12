package com.campusmaster.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard")
@CrossOrigin
public class DashboardController {

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        // TODO: replace with real computations
        Map<String, Object> stats = Map.of(
                "activeStudents", 120,
                "submissionRate", 0.87,
                "averageGrade", 14.5
        );
        return ResponseEntity.ok(stats);
    }
}
