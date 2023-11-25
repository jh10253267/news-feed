package com.sparta.newsfeed.board.service;

import com.sparta.newsfeed.board.domain.Board;
import com.sparta.newsfeed.board.dto.BoardRequestDTO;
import com.sparta.newsfeed.board.dto.BoardResponseDTO;
import com.sparta.newsfeed.board.repository.BoardRepository;
import com.sparta.newsfeed.member.domain.Member;
import com.sparta.newsfeed.member.repository.MemberRepository;
import com.sparta.newsfeed.member.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardServiceTest {
    @Autowired
    BoardService boardService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;

    @DisplayName("[Board] [Service] [Register]")
    @Test
    @Transactional
    void testRegister() {
        BoardRequestDTO boardRegisterDTO = BoardRequestDTO.builder()
                .title("register service test")
                .content("register service test")
                .build();
//        boardService.register(boardRegisterDTO);
    }

    @DisplayName("[Board] [Service] [ReadOne]")
    @Test
    void testRead() {
        Long id = 1L;

        Optional<Board> result = boardRepository.findById(id);
        Board board = result.orElseThrow();
        log.info(board.getMember());
    }

    @DisplayName("[Board] [Service] [Modify]")

    @Test
    void testModify() {
        Long id = 1L;
        String title = "modify test";

        Optional<Member> result =  memberRepository.findByUsername("user1");
        Member member = result.orElseThrow();

        BoardRequestDTO boardRequestDTO = BoardRequestDTO.builder()
                .title(title)
                .content("modi test")
                .build();

        boardService.modify(id, boardRequestDTO, member);
    }
    @DisplayName("[Board] [Service] [Delete]")
    @Test
    void testDelete() {
        Long id = 1L;
        Optional<Member> result =  memberRepository.findByUsername("user1");
        Member member = result.orElseThrow();
        boardService.delete(id, member);
    }

}