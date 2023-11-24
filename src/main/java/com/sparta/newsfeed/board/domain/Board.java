package com.sparta.newsfeed.board.domain;

import com.sparta.newsfeed.domain.BaseEntity;
import com.sparta.newsfeed.comment.domain.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String writer;
    @OneToMany(mappedBy = "board",
    cascade = {CascadeType.ALL},
    fetch = FetchType.LAZY)
    private List<Comment> comment;
    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
