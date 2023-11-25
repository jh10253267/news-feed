package com.sparta.newsfeed.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.newsfeed.domain.BaseEntity;
import com.sparta.newsfeed.comment.domain.Comment;
import com.sparta.newsfeed.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;
    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
