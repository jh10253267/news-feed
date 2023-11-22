package com.sparta.newsfeed.board.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardRequestDTO {
    private String title;
    private String content;

}
