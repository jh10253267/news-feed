package com.sparta.newsfeed.board.service;

import com.sparta.newsfeed.board.domain.Board;
import com.sparta.newsfeed.board.dto.BoardRequestDTO;
import com.sparta.newsfeed.board.dto.BoardResponseDTO;
import com.sparta.newsfeed.board.repository.BoardRepository;
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

    public Long register(BoardRequestDTO boardRequestDTO) {
        Board board = Board.builder()
                .title(boardRequestDTO.getTitle())
                .content(boardRequestDTO.getContent())
                .build();
        Long id = boardRepository.save(board).getId();
        return id;
    }
    @Transactional(readOnly = true)
    public BoardResponseDTO read(Long id) {
        Optional<Board> result = boardRepository.findById(id);
        Board board = result.orElseThrow();

        BoardResponseDTO boardResponseDTO = modelMapper.map(board, BoardResponseDTO.class);
        return boardResponseDTO;
    }
    public BoardResponseDTO modify(Long id, BoardRequestDTO boardRequestDTO) {
        Optional<Board> result = boardRepository.findById(id);
        Board board = result.orElseThrow();

        board.change(boardRequestDTO.getTitle(), boardRequestDTO.getContent());
        Board modBoard = boardRepository.save(board);
        return modelMapper.map(modBoard, BoardResponseDTO.class);
    }
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
