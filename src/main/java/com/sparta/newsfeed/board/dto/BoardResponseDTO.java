package com.sparta.newsfeed.board.dto;

import com.sparta.newsfeed.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDTO {
    private String title;
    private String content;
    private Long userId;
    private List<String> fileNames;
    private LocalDateTime createdAt;
}
