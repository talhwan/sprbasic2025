package com.thc.sprbasic2025.service.impl;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.repository.BoardRepository;
import com.thc.sprbasic2025.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardServiceimpl implements BoardService {

    final BoardRepository boardRepository;
    public BoardServiceimpl(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    @Override
    public Long create(Map<String, Object> param) {
        String title = (String) param.get("title");
        String content = (String) param.get("content");
        String author = (String) param.get("author");

        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setAuthor(author);

        boardRepository.save(board);

        return board.getId();
    }

    @Override
    public List<Board> list() {
        List<Board> list = boardRepository.findAll();
        return list;
    }

    @Override
    public Board detail(Long id) {
        //Optional<Board> board = boardRepository.findById(id);
        Board board = boardRepository.findById(id).orElse(null);
        return board;
    }
}
