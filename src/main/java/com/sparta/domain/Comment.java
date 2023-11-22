package com.sparta.domain;

import com.sparta.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.User;

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
    
    @Column(nullable = false)
    private String writer; // 작성자


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
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
