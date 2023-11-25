package com.sparta.newsfeed.member.dto;

import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDTO {
    private String username;
    private String content;
}
