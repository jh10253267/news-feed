package com.sparta.newsfeed.board.service;

import com.sparta.newsfeed.board.domain.Board;
import com.sparta.newsfeed.board.dto.BoardRequestDTO;
import com.sparta.newsfeed.board.dto.BoardResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardServiceTest {
    @Autowired
    BoardService boardService;

    @DisplayName("[Board] [Service] [Register]")
    @Test
    @Transactional
    void testRegister() {
        BoardRequestDTO boardRegisterDTO = BoardRequestDTO.builder()
                .title("register service test")
                .content("register service test")
                .build();
        boardService.register(boardRegisterDTO);
    }

    @DisplayName("[Board] [Service] [ReadOne]")
    @Test
    void testRead() {
        Long id = 1L;

        BoardResponseDTO boardResponseDTO = boardService.read(id);
        log.info(boardResponseDTO);
    }

    @DisplayName("[Board] [Service] [Modify]")
    @Transactional
    @Test
    void testModify() {
        Long id = 1L;
        String title = "moodify test";
        BoardRequestDTO boardRequestDTO = BoardRequestDTO.builder()
                .title(title)
                .content("modi test")
                .build();
        boardService.modify(id, boardRequestDTO);
    }
    @DisplayName("[Board] [Service] [Modify]")
    @Transactional
    @Test
    void testDelete() {
        Long id = 1L;
        boardService.delete(id);

    }

}