package com.campusmaster.presentation.dto.crud;

import lombok.Data;

@Data
public class DiscussionRequest {
    private String sujet;
    private Long coursId;
}
