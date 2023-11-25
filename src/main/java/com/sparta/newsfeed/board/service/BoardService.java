package com.sparta.newsfeed.board.service;

import com.sparta.newsfeed.board.domain.Board;
import com.sparta.newsfeed.board.dto.BoardRequestDTO;
import com.sparta.newsfeed.board.dto.BoardResponseDTO;
import com.sparta.newsfeed.board.repository.BoardRepository;
import com.sparta.newsfeed.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    public void register(BoardRequestDTO boardRequestDTO, Member member) {
        Board board = Board.builder()
                .title(boardRequestDTO.getTitle())
                .content(boardRequestDTO.getContent())
                .member(member)
                .build();
        boardRepository.save(board).getId();
    }
    @Transactional(readOnly = true)
    public BoardResponseDTO read(Long id) {
        Optional<Board> result = boardRepository.findById(id);
        Board board = result.orElseThrow();

        BoardResponseDTO boardResponseDTO = modelMapper.map(board, BoardResponseDTO.class);
        return boardResponseDTO;
    }
    public BoardResponseDTO modify(Long id, BoardRequestDTO boardRequestDTO, Member member) {
        Optional<Board> result = boardRepository.findById(id);
        Board board = result.orElseThrow();

        if(member.getUsername().equals(board.getMember().getUsername())) {
            board.change(boardRequestDTO.getTitle(), boardRequestDTO.getContent());
            Board modBoard = boardRepository.save(board);
            return modelMapper.map(modBoard, BoardResponseDTO.class);
        } else {
            throw new RuntimeException("작성자만 자신의 게시물을 수정할 수 있습니다.");
        }

    }
    public void delete(Long id, Member member) {
        Optional<Board> result = boardRepository.findById(id);
        Board board = result.orElseThrow();

        if(member.getUsername().equals(board.getMember().getUsername())) {
            boardRepository.deleteById(id);
        } else {
            throw new RuntimeException("자신의 게시물만 삭제할 수 있습니다.");
        }

    }
}
