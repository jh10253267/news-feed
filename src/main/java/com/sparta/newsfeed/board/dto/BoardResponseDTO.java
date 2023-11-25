package com.sparta.newsfeed.board.dto;

import com.sparta.newsfeed.comment.domain.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardResponseDTO {
    private String title;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
