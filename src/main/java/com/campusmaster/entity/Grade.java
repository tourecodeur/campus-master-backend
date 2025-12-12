package com.campusmaster.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double score;

    @Column(length = 2000)
    private String feedback;

    @OneToOne
    @JoinColumn(name = "submission_id")
    private Submission submission;
}
