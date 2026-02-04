package com.campusmaster.presentation.dto.crud;

import lombok.Data;

@Data
public class MessageDiscussionRequest {
    private String contenu;
    private Long auteurId;
    private Long discussionId;
}
