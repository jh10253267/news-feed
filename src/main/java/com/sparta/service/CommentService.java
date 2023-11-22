package com.sparta.service;

import com.sparta.domain.Board;
import com.sparta.domain.Comment;
import com.sparta.domain.Member;
import com.sparta.dto.CommentRequestDto;
import com.sparta.dto.CommentResponseDto;
import com.sparta.repository.BoardRepository;
import com.sparta.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private BoardRepository boardRepository;


    // 댓글 작성
    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, Member member) {
        // 게시글 있는 지 확인
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Comment comment = new Comment(member, commentRequestDto, board);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }


    // 댓글 조회
    public List<CommentResponseDto> getComments(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("존재하지 않습니다")
        );

        List<Comment> commentList = commentRepository.findByBoardOrderByCreatedAtDesc(board);
        List<CommentResponseDto> responseDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            responseDtoList.add(new CommentResponseDto(comment));
        }

        return responseDtoList;
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, Member member) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글이 존재하지 않습니다."));

        // 댓글 작성자와 입력값 작성자 비교, 일치하면 ㄱㄱ
        if (comment.getMember().getUsername().equals(member.getUsername())) {
            comment.update(commentRequestDto);
            return new CommentResponseDto(comment);
        } else {
            throw new IllegalArgumentException("작성자만 수정이 가능합니다.");
        }
    }

    // 댓글 삭제
    public void deleteComment(Long id, Member member) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글이 존재하지 않습니다."));

        // 댓글 작성자와 입력값 작성자 비교, 일치하면 ㄱㄱ
        if (comment.getMember().getUsername().equals(member.getUsername())) {
            commentRepository.delete(comment);
        } else {
            throw new IllegalArgumentException("작성자만 수정이 가능합니다.");
        }
    }
}
