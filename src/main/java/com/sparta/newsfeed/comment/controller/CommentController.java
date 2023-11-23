package com.sparta.newsfeed.comment.controller;


import com.sparta.newsfeed.comment.dto.CommentRequestDto;
import com.sparta.newsfeed.comment.dto.CommentResponseDto;
import com.sparta.newsfeed.comment.service.CommentService;
import com.sparta.newsfeed.member.domain.Member;
import com.sparta.newsfeed.security.service.MemberDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/{boardId}")
    public CommentResponseDto createComment(@PathVariable Long boardId, @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return commentService.createComment(boardId, commentRequestDto, memberDetails.getMember());
    }

    @PutMapping("/comment/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return commentService.updateComment(commentId, commentRequestDto, memberDetails.getMember());
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        commentService.deleteComment(commentId, memberDetails.getMember());
    }
}
