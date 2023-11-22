package com.sparta.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class Comment extends BaseEntity implements Comparable<Comment>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String writer;
    @ManyToOne
    private Board board;
    private int ord;

    @Override
    public int compareTo(Comment other) {
        return this.ord = other.ord;
    }
}
