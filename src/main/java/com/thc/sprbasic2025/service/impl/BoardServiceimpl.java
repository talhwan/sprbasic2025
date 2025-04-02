package com.thc.sprbasic2025.service.impl;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.dto.BoardDto;
import com.thc.sprbasic2025.repository.BoardRepository;
import com.thc.sprbasic2025.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardServiceimpl implements BoardService {

    final BoardRepository boardRepository;
    public BoardServiceimpl(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    @Override
    public BoardDto.CreateResDto create(BoardDto.CreateReqDto param) {
        return boardRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(BoardDto.UpdateReqDto param) {
        Board board = boardRepository.findById(param.getId()).orElse(null);
        if(board == null){
            throw new RuntimeException("no data");
        }
        if(param.getDeleted() != null){ board.setDeleted(param.getDeleted()); }
        if(param.getTitle() != null){ board.setTitle(param.getTitle()); }
        if(param.getContent() != null){ board.setContent(param.getContent()); }
        if(param.getAuthor() != null){ board.setAuthor(param.getAuthor()); }
        boardRepository.save(board);
    }

    @Override
    public void delete(BoardDto.DeleteReqDto param) {
        update(BoardDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    @Override
    public List<BoardDto.DetailResDto> list() {
        List<Board> list = boardRepository.findAll();
        List<BoardDto.DetailResDto> newList = new ArrayList<>();
        for(Board each : list){
            newList.add(detail(BoardDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }

    @Override
    public BoardDto.DetailResDto detail(BoardDto.DetailReqDto param) {
        Board board = boardRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException(""));
        BoardDto.DetailResDto res = BoardDto.DetailResDto.builder()
                .id(param.getId())
                .deleted(board.getDeleted())
                .title(board.getTitle()).content(board.getContent()).author(board.getAuthor())
                .countread(board.getCountread())
                .build();
        return res;
    }
}
