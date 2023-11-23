package com.sparta.newsfeed.member.dto;

import lombok.Getter;

@Getter
public class RequestProfileUpdateDto {

    private String username;
    private String password;
    private String content;
    private String passwordConfirm;
}
