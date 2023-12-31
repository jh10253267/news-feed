package com.sparta.newsfeed.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {
    @NotBlank
    private String content;
}
