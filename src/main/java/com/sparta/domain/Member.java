package com.sparta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseEntity{
    @Id
    private String username;

    private String password;

    private String content;
    public void change(String password){
        this.password = password;
    }

    public void changeContent(String contentStr){
        this.content = contentStr;
    }
}
