package com.sparta.repository;

import com.sparta.domain.Board;
import com.sparta.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardOrderByCreatedAtDesc(Board board); // 작성일 기준 내림차순 정렬
}
