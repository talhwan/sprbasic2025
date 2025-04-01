package com.thc.sprbasic2025.service.impl;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.repository.BoardRepository;
import com.thc.sprbasic2025.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public void update(Map<String, Object> param) {
        Long id = Long.parseLong(param.get("id") + "");
        System.out.println(param.get("deleted"));
        // 스트링으로는 값이 잘 오는데.. 블리언 바꾸는거 어떻게 할까요?!
        Boolean deleted = null;
        if(param.get("deleted") != null){
            if("true".equals(param.get("deleted") + "")){
                deleted = true;
            } else {
                deleted = false;
            }
        }
        String title = (String) param.get("title");
        String content = (String) param.get("content");
        String author = (String) param.get("author");

        Board board = boardRepository.findById(id).orElse(null);
        if(board == null){
            throw new RuntimeException("no data");
        }
        if(deleted != null){ board.setDeleted(deleted); }
        if(title != null){ board.setTitle(title); }
        if(content != null){ board.setContent(content); }
        if(author != null){ board.setAuthor(author); }
        boardRepository.save(board);
    }

    @Override
    public void delete(Long id) {
        Board board = null;
        /*
        // 1. 디비에서도 그냥 지워버리기!
        board = boardRepository.findById(id).orElse(null);
        if(board == null){
            throw new RuntimeException("no data");
        }
        boardRepository.delete(board);
        // 2. 디비에서 실제로는 안지우고, 지웠다는 기록 남겨두기
        // 2-1) 여기서 직접 바꿔보기!
        board = boardRepository.findById(id).orElse(null);
        if(board == null){
            throw new RuntimeException("no data");
        }
        board.setDeleted(true);
        boardRepository.save(board);
        */
        // 2-2) 업데이트 한테 부탁해보기!
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("deleted", true);
        update(param);
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
