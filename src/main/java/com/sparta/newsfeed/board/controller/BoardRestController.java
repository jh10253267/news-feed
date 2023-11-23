package com.sparta.newsfeed.board.controller;

import com.sparta.newsfeed.board.dto.BoardRequestDTO;
import com.sparta.newsfeed.board.dto.BoardResponseDTO;
import com.sparta.newsfeed.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, Object>> register(@RequestBody BoardRequestDTO boardRequestDTO) {
        log.info(boardRequestDTO);
        Map<String, Object> map = new HashMap<>();
        Long id = boardService.register(boardRequestDTO);
        map.put("id", id);
        return ResponseEntity.ok(map);
    }
    @GetMapping("/{id}")
    public Map<String, Object> read(@PathVariable("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        BoardResponseDTO boardResponseDTO = boardService.read(id);
        map.put("board",boardResponseDTO);
        return map;
    }
    @PutMapping("/{id}")
    public Map<String, Object> modify(@PathVariable("id") Long id, @RequestBody BoardRequestDTO boardRequestDTO) {
        Map<String, Object> map = new HashMap<>();

        BoardResponseDTO boardResponseDTO = boardService.modify(id, boardRequestDTO);

        map.put("board",boardRequestDTO);
        return map;
    }
    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return Map.of("result", "success");
    }
}
