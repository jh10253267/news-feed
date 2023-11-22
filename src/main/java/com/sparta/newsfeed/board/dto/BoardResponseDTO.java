package com.sparta.newsfeed.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardResponseDTO {
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
}
