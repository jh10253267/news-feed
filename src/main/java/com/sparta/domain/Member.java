package com.sparta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity{
    @Id
    private String username;

    private String password;

    private String content;

    public void changePassword(String password) {
        this.password = password;
    }
    public void changeContent(String content) {
        this.content = content;
    }
}
