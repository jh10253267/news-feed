package com.sparta.newsfeed.board.controller;

import com.sparta.newsfeed.board.dto.BoardRequestDTO;
import com.sparta.newsfeed.board.dto.BoardResponseDTO;
import com.sparta.newsfeed.board.service.BoardService;
import com.sparta.newsfeed.security.service.MemberDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/boards")
public class BoardRestController {
    private final BoardService boardService;

    @Operation(summary = "게시글 등록", description = "로그인한 사용자는 게시판에 게시글을 등록할 수 있다.")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> register(@RequestBody BoardRequestDTO boardRequestDTO,
                                                        @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        log.info(boardRequestDTO);
        Map<String, Object> map = new HashMap<>();
        boardService.register(boardRequestDTO, memberDetails.getMember());
        map.put("result", "success");
        return ResponseEntity.ok(map);
    }
    @Operation(summary = "게시글 조회", description = "게시글 고유 번호를 통해 게시글을 조회할 수 있다.")
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> read(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        BoardResponseDTO boardResponseDTO = boardService.read(id);
        map.put("board",boardResponseDTO);
        return ResponseEntity.ok(map);
    }
    @Operation(summary = "게시글 수정", description = "로그인한 사용자는 자신의 게시물을 수정할 수 있다.")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> modify(@PathVariable("id") Long id, @RequestBody BoardRequestDTO boardRequestDTO,
                                      @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        Map<String, Object> map = new HashMap<>();

        boardService.modify(id, boardRequestDTO, memberDetails.getMember());

        map.put("result","success");
        return ResponseEntity.ok(map);
    }
    @Operation(summary = "게시글 수정", description = "로그인한 사용자는 자신의 게시물을 삭제할 수 있다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Long id,
                                      @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        boardService.delete(id, memberDetails.getMember());
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return ResponseEntity.ok(map);
    }
}
