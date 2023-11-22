package com.sparta;

import com.sparta.domain.Board;
import com.sparta.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@SpringBootTest
class NewsFeedApplicationTests {

    private final BoardRepository boardRepository;

    @Test
    void contextLoads() {

    }

    @Transactional
    @DisplayName("[Board] [Repository] [Update]")
    @Test
    void testUpdate() {
        Long id = 1L;
        String title = "update test title...";
        String content = "update test content...";
        Optional<Board> result = boardRepository.findById(id);
        Board board  = result.orElseThrow();
        //board.change(title, content);
        Board updatedBoard = boardRepository.save(board);
        assertThat(updatedBoard.getTitle()).isEqualTo(title);
    }
}
