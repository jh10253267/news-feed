package com.sparta.newsfeed.board.repository;

import com.sparta.newsfeed.board.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @DisplayName("[Board] [Repository] [Insert]")
    @Test
    void testInsert() {
        int dataSize = 50;
        IntStream.rangeClosed(1, dataSize).forEach(i -> {
            Board board = Board.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .writer("user" + (i % 10))
                    .build();
            Board result = boardRepository.save(board);
            log.info("Id: " + result.getId());
        });
            List<Board> boards = boardRepository.findAllByOrderByIdDesc();
        assertThat(boards.size()).isEqualTo(dataSize);

    }

    @Transactional
    @DisplayName("[Board] [Repository] [Update]")
    @Test
    void testUpdate() {
        Long id = 1L;
        String title = "update test title...";
        String content = "update test content";

        Optional<Board> result = boardRepository.findById(id);
        Board board = result.orElseThrow();

        board.change(title, content);
        Board updatedBoard = boardRepository.save(board);

        assertThat(updatedBoard.getTitle()).isEqualTo(title);
    }
    @Transactional
    @DisplayName("[Board] [Repository] [Delete]")
    @Test
    void testDelete() {
        Long id = 1L;
        boardRepository.deleteById(id);
        Optional<Board> result = boardRepository.findById(id);
        assertThatCode(result::orElseThrow).isInstanceOf(NoSuchElementException.class);
    }
    @DisplayName("[Board] [Repository] [Read]")
    @Test
    void testRead() {
        Long id = 1L;
        Optional<Board> result = boardRepository.findById(id);
        Board board = result.orElseThrow();
        log.info(board);
    }
    @DisplayName("[Board] [Repository] [ReadAll]")
    @Test
    void testReadAll() {
        List<Board> boards = boardRepository.findAllByOrderByIdDesc();
        assertThat(boards.size()).isEqualTo(50);
    }
}