package com.sparta.newsfeed.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardRequestDTO {
    @NotBlank
    private String title;
    @NotNull
    private String content;

}
