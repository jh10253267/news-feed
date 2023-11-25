package com.sparta.newsfeed.board.controller;

import com.sparta.newsfeed.board.dto.BoardRequestDTO;
import com.sparta.newsfeed.board.dto.BoardResponseDTO;
import com.sparta.newsfeed.board.service.BoardService;
import com.sparta.newsfeed.security.service.MemberDetailsImpl;
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

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> register(@RequestBody BoardRequestDTO boardRequestDTO,
                                                        @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        log.info(boardRequestDTO);
        Map<String, Object> map = new HashMap<>();
        boardService.register(boardRequestDTO, memberDetails.getMember());
        map.put("result", "success");
        return ResponseEntity.ok(map);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> read(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        BoardResponseDTO boardResponseDTO = boardService.read(id);
        map.put("board",boardResponseDTO);
        return ResponseEntity.ok(map);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> modify(@PathVariable("id") Long id, @RequestBody BoardRequestDTO boardRequestDTO,
                                      @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        Map<String, Object> map = new HashMap<>();

        boardService.modify(id, boardRequestDTO, memberDetails.getMember());

        map.put("result","success");
        return ResponseEntity.ok(map);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Long id,
                                      @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        boardService.delete(id, memberDetails.getMember());
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return ResponseEntity.ok(map);
    }
}
