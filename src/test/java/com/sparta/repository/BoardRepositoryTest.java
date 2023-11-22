package com.sparta.repository;

import com.sparta.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;
    @Test
    void insertDummyData() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
            Board board = Board.builder()
                    .title("title..."+i)
                    .content("content..."+i)
                    .writer("user"+(i%10))
                    .build();
            Board result = boardRepository.save(board);
            log.info("Id: "  + result.getId());
        });
    }
}