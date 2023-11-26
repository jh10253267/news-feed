package com.sparta.newsfeed.comment.repository;

import com.sparta.newsfeed.board.domain.Board;
import com.sparta.newsfeed.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardOrderByCreatedAtDesc(Board board); // 작성일 기준 내림차순 정렬

    void deleteByBoard_Id(Long id);

}
