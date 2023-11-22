package com.sparta.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "comments")
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String writer;
    @OneToMany(mappedBy = "board",
    cascade = {CascadeType.ALL},
    fetch = FetchType.LAZY)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();
    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
