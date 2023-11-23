package com.sparta.newsfeed.comment.domain;

import com.sparta.newsfeed.board.domain.Board;
import com.sparta.newsfeed.domain.BaseEntity;
import com.sparta.newsfeed.comment.dto.CommentRequestDto;
import com.sparta.newsfeed.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 댓글 번호

    @Column(nullable = false)
    private String content; // 댓글 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "??", nullable = false)
    private Member member;




    public Comment(Member member, CommentRequestDto commentRequestDto, Board board) {
        this.member = member;
        this.board = board;
        this.content = commentRequestDto.getContent();
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }


}
