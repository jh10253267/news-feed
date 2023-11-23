package com.sparta.newsfeed.board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class BoardRequestDTO {
    private String title;
    private String content;

}
